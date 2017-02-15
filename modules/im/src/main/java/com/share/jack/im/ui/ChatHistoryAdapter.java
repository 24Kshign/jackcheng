package com.share.jack.im.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chat.ImageMessageBody;
import com.easemob.chat.TextMessageBody;
import com.jack.mc.cyg.cygtools.util.CygTime;
import com.share.jack.easeui.utils.EaseSmileUtils;
import com.share.jack.im.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 */
public class ChatHistoryAdapter extends ArrayAdapter<EMConversation> {

    private static final String TAG = "ChatHistoryAdapter";

    private LayoutInflater inflater;
    private List<EMConversation> conversationList;
    private List<EMConversation> copyConversationList;
    //    private List<UserProfile> chList;
    private ConversationFilter conversationFilter;
    private boolean notiyfyByFilter;

    public ChatHistoryAdapter(Context context, int textViewResourceId, List<EMConversation> objects) {
        super(context, textViewResourceId, objects);
        this.conversationList = objects;
        copyConversationList = new ArrayList<>();
        copyConversationList.addAll(objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.em_recent_contact_list_item, parent, false);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.list_item_message_icon);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.list_item_message_title);
            holder.tvRemark = (TextView) convertView.findViewById(R.id.list_item_message_remark);
            holder.tvTime = (TextView) convertView.findViewById(R.id.list_item_message_time);
            holder.ivDot = (ImageView) convertView.findViewById(R.id.mlc_iv_dot);
            convertView.setTag(holder);
        }
        EMConversation conversation = getItem(position);
        // 获取用户username或者群组groupid
//        if (chList.size() == 0) {
//            holder.ivIcon.setMsgAvatar("");
//            holder.tvTitle.setText(conversationList.get(position).getUserName());
//        } else {
//            holder.ivIcon.setMsgAvatar(chList.get(position).getImageLogo());
//            holder.tvTitle.setText(chList.get(position).getNickName());
//        }
        holder.ivIcon.setImageResource(R.drawable.ic_launcher);
        holder.tvTitle.setText(conversationList.get(position).getUserName());
        holder.ivDot.setVisibility(conversation.getUnreadMsgCount() > 0 ? View.VISIBLE : View.GONE);

        if (conversation.getMsgCount() != 0) {
            // 把最后一条消息的内容作为item的message内容
            EMMessage lastMessage = conversation.getLastMessage();
            holder.tvRemark.setText(EaseSmileUtils.getSmiledText(getContext(), getMessageDigest(lastMessage, (this.getContext()))),
                    TextView.BufferType.SPANNABLE);
            holder.tvTime.setText(CygTime.formatDateTime(new Date(lastMessage.getMsgTime())));
        }
        return convertView;
    }

    private static class ViewHolder {
        //用户名
        TextView tvTitle;
        //消息
        TextView tvRemark;
        //时间
        TextView tvTime;
        //头像
        ImageView ivIcon;
        //未读标志
        ImageView ivDot;
    }

    /**
     * 根据消息内容和消息类型获取消息内容提示
     *
     * @param message
     * @param context
     * @return
     */
    private String getMessageDigest(EMMessage message, Context context) {
        String digest = "";
        switch (message.getType()) {
            case LOCATION: // 位置消息
                if (message.direct == EMMessage.Direct.RECEIVE) {
                    // 从sdk中提到了ui中，使用更简单不犯错的获取string的方法
                    // digest = EasyUtils.getAppResourceString(context,
                    // "location_recv");
                    digest = getStrng(context, R.string.location_recv);
                    digest = String.format(digest, message.getFrom());
                    return digest;
                } else {
                    // digest = EasyUtils.getAppResourceString(context,
                    // "location_prefix");
                    digest = getStrng(context, R.string.location_prefix);
                }
                break;
            case IMAGE: // 图片消息
                ImageMessageBody imageBody = (ImageMessageBody) message.getBody();
                digest = getStrng(context, R.string.picture) + imageBody.getFileName();
                break;
            case VOICE:// 语音消息
                digest = getStrng(context, R.string.voice);
                break;
            case VIDEO: // 视频消息
                digest = getStrng(context, R.string.video);
                break;
            case TXT: // 文本消息
                TextMessageBody txtBody = (TextMessageBody) message.getBody();
                digest = txtBody.getMessage();
                break;
            case FILE: // 普通文件消息
                digest = getStrng(context, R.string.file);
                break;
            default:
                Log.e(TAG, "getMessageDigest: " + "unknow type");
                return "";
        }

        return digest;
    }

    String getStrng(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    @Override
    public Filter getFilter() {
        if (conversationFilter == null) {
            conversationFilter = new ConversationFilter(conversationList);
        }
        return conversationFilter;
    }

    private class ConversationFilter extends Filter {
        List<EMConversation> mOriginalValues = null;

        public ConversationFilter(List<EMConversation> mList) {
            mOriginalValues = mList;
        }

        //执行筛选
        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (mOriginalValues == null) {
                mOriginalValues = new ArrayList<>();
            }
            if (prefix == null || prefix.length() == 0) {
                results.values = copyConversationList;
                results.count = copyConversationList.size();
            } else {
                String prefixString = prefix.toString();
                final int count = mOriginalValues.size();
                final ArrayList<EMConversation> newValues = new ArrayList<EMConversation>();
                for (int i = 0; i < count; i++) {
                    final EMConversation value = mOriginalValues.get(i);
                    String username = value.getUserName();
                    // First match against the whole ,non-splitted value
                    if (username.startsWith(prefixString)) {
                        newValues.add(value);
                    } else {
                        final String[] words = username.split(" ");
                        final int wordCount = words.length;
                        // Start at index 0, in case valueText starts with space(s)
                        for (int k = 0; k < wordCount; k++) {
                            if (words[k].startsWith(prefixString)) {
                                newValues.add(value);
                                break;
                            }
                        }
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        //筛选结果
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            conversationList.clear();
            conversationList.addAll((List<EMConversation>) results.values);
            if (results.count > 0) {
                notiyfyByFilter = true;
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!notiyfyByFilter) {
            copyConversationList.clear();
            copyConversationList.addAll(conversationList);
            notiyfyByFilter = false;
        }
    }
}
