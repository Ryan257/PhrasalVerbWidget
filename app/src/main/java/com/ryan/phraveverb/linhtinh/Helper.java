package com.ryan.phraveverb.linhtinh;

import com.ryan.phraveverb.linhtinh.Verb;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandy on 5/31/15.
 */
public class Helper {

    public static final String VERBS_JSON = "[ { \"verb\": \"Get about\", \"meaning\": \"Lan truyền\" }, { \"verb\": \"Get ahead\", \"meaning\": \"Tiến bộ\" }, { \"verb\": \"Get at sth\", \"meaning\": \"Tìm ra, khám phá ra\" }, { \"verb\": \"Get at sb\", \"meaning\": \"Chỉ trích, công kích\" }, { \"verb\": \"Get away from \", \"meaning\": \"Trốn thoát\" }, { \"verb\": \"Ge away with\", \"meaning\": \"Thoát khỏi (sự trừng phạt)\" }, { \"verb\": \"Get back\", \"meaning\": \"Trở về\" }, { \"verb\": \"Get sth back\", \"meaning\": \"Lấy lại\" }, { \"verb\": \"Get behind\", \"meaning\": \"Chậm trễ\" }, { \"verb\": \"Get down\", \"meaning\": \"Làm nản lòng\" }, { \"verb\": \"Get down to sth\", \"meaning\": \"Bắt tay vào việc gì\" }, { \"verb\": \"Get in/into sth\", \"meaning\": \"Được nhận vào\" }, { \"verb\": \"Get off\", \"meaning\": \"Rời khỏi, xuống (xe, máy bay)\" }, { \"verb\": \"Get on\", \"meaning\": \"Lên (tàu xe, …)\" }, { \"verb\": \"Get on with\", \"meaning\": \"Hoà thuận\" }, { \"verb\": \"Get out\", \"meaning\": \"lộ ra ngoài (tin tức)\" }, { \"verb\": \"Get out off\", \"meaning\": \"Lẩn tránh\" }, { \"verb\": \"Get over\", \"meaning\": \"Phục hồi, vượt qua\" }, { \"verb\": \"Get through\", \"meaning\": \"Vượt qua\" }, { \"verb\": \"Get through to sb\", \"meaning\": \"Làm ai hiểu được điều gì\" }, { \"verb\": \"Get together\", \"meaning\": \"Tụ họp\" }, { \"verb\": \"Get up to\", \"meaning\": \"Gây ra\" }, { \"verb\": \"Give up\", \"meaning\": \"Từ bỏ\" }, { \"verb\": \"Take off\", \"meaning\": \"Cởi ra\" }, { \"verb\": \"Wash up\", \"meaning\": \"Rửa chén\" }, { \"verb\": \"Go on\", \"meaning\": \"Tiếp tục\" }, { \"verb\": \"Look up\", \"meaning\": \"Tra (từ điển)\" }, { \"verb\": \"Put on\", \"meaning\": \"Mặc, đội vào\" }, { \"verb\": \"Turn on\", \"meaning\": \"Mở, bật (quạt, đèn …)\" }, { \"verb\": \"Turn off\", \"meaning\": \"Tắt (quạt, đèn …)\" }, { \"verb\": \"Turn up\", \"meaning\": \"Đến, có mặt\" }, { \"verb\": \"Turn around\", \"meaning\": \"Quay lại\" }, { \"verb\": \"Lie down\", \"meaning\": \"Nằm xuống\" }, { \"verb\": \"Look after\", \"meaning\": \"Chăm sóc\" }, { \"verb\": \"Take after\", \"meaning\": \"Giống\" }, { \"verb\": \"Go off\", \"meaning\": \"Chém gió\" }, { \"verb\": \"Try out\", \"meaning\": \"Thử\" }, { \"verb\": \"Hold up\", \"meaning\": \"Nhanh lên\" }, { \"verb\": \"Hurry up\", \"meaning\": \"Nhanh lên\" }, { \"verb\": \"Catch sight of\", \"meaning\": \"Bắt gặp\" }, { \"verb\": \"Lose sight of\", \"meaning\": \"Mất hút\" }, { \"verb\": \"Make fun of\", \"meaning\": \"Chế giễu \" }, { \"verb\": \"Lose track of\", \"meaning\": \"Mất dấu\" }, { \"verb\": \"Take account of\", \"meaning\": \"Lưu tâm\" }, { \"verb\": \"Take note of\", \"meaning\": \"Để ý\" }, { \"verb\": \"Take care of\", \"meaning\": \"Chăm sóc\" }, { \"verb\": \"Take advantage of\", \"meaning\": \"Lợi dụng\" }, { \"verb\": \"Take leave of\", \"meaning\": \"Từ biệt\" }, { \"verb\": \"Get about\", \"meaning\": \"Lan truyền\" }, { \"verb\": \"Give birth to\", \"meaning\": \"Sinh con\" }, { \"verb\": \"Give place to\", \"meaning\": \"Nhường chỗ\" } ]";

    public static final int[] COLORS = new int[] {
            0xffE91E63,
            0xffF44336,
            0xff9C27B0,
            0xff00BCD4,
            0xff3F51B5,
            0xffFF5722
    };

    private static List<Verb> verbList = new ArrayList<Verb>();
    public static List<Verb> getVerbList() {
        try {
            JSONArray jsonArray = new JSONArray(VERBS_JSON);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Verb verb = new Verb();
                verb.word = jsonObject.getString("verb");
                verb.meaning = jsonObject.getString("meaning");
                verbList.add(verb);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return verbList;
    }


    public static int getRandomVerbIndex () {
        return (int )(Math.random() * 50);
    }

    public static int getRandomColor() {
        return COLORS[(int )(Math.random() * 5)];
    }
}
