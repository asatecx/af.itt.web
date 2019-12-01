package af.itt.sc.comon;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * メッセージクラス
 *
 * @author 東山
 */
public class Message {

    private String code = null;
    private Message.Type type = null;
    private String message = null;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * メッセージコード、メッセージ引数からインスタンスを生成します。<br>
     * メッセージコード中の種別（S, I, W, E）を判別しメッセージ種別を設定します。
     *
     * @param messageCode メッセージコード
     * @param messageArgs メッセージ引数
     */
    public Message(String messageCode, String... messageArgs) {
        this.code = messageCode;
        this.message = messageArgs[0];
        this.type = Message.Type.type(messageCode);
    }

    public Message() {
        this.code = "";
        this.message = "";
    }

    /**
     * メッセージ種別、メッセージコード、メッセージ引数からインスタンスを生成します。<br>
     * メッセージコードからメッセージ種別を判別できない場合に利用します。
     *
     * @param type メッセージ種別
     * @param messageCode メッセージコード
     * @param messageArgs メッセージ引数
     */
    public Message(Message.Type type, String messageCode, String... messageArgs) {
        this.code = messageCode;
        this.message =  messageArgs[0];
        this.type = type;
    }

    Message(String code, String message, Message.Type type) {
        this.type = type;
        this.code = code;
        this.message = message;
    }

    /**
     * メッセージ種別、メッセージからインスタンスを生成します。
     *
     * @param type メッセージ種別
     * @param message メッセージ
     */
    public Message(Message.Type type, String message) {
        this.message = message;
        this.type = type;
    }

    private String getCode() {
        return this.code;
    }

    private String getShortMessage() {
        return message;
    }

    //@JsonIgnore
    public String getMessage() {
        return String.format("[%s]%s", this.code, this.message);
    }

    //@JsonProperty("type")
    public Message.Type getType() {
        return type;
    }

    public void setType(Message.Type type) {
        this.type = type;
    }

    public enum Type {

        SUCCESS("S", "success"), INFO("I", "info"), WARNING("W", "warning"), DANGER("E", "danger");

        private String type = null;
        private String typeName = null;

        private Type(String type, String name) {
            this.type = type;
            this.typeName = name;
        }

        static Type type(String messageCode) {
            for (Type type : values()) {
                if (type.type.equals(StringUtils.mid(messageCode, 0, 1))) {
                    return type;
                }
            }
            return Type.INFO;
        }
    }

    @Component
    private static class Utils {
        static MessageSource messageSource;
        @Autowired
        private MessageSource wiredMessage;


        private void init() {
            messageSource = wiredMessage;
        }

        static String get(String code, String... arg) {
            return messageSource.getMessage(code, arg, Locale.getDefault());
        }

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}