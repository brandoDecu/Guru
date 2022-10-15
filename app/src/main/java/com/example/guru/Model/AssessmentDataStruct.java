package com.example.guru.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "assessment_table")
public class AssessmentDataStruct {

    @PrimaryKey(autoGenerate = true)
    private int roomId;

    @Ignore
    private Date created;
    @Ignore
    private Date updated;
    @Ignore
    private String objectId;


    private String userEmail;
    private long dateCreated;
    private boolean uploaded;

    private int option0 = -1;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    private int option1 = -1;
    private int option2 = -1;
    private int option3 = -1;
    private int option4 = -1;
    private int option5 = -1;
    private int option6 = -1;
    private int option7 = -1;
    private int option8 = -1;
    private int option9 = -1;
    private int option10 = -1;
    private int option11 = -1;
    private int option12 = -1;
    private int option13 = -1;
    private int option14 = -1;
    private int option15 = -1;
    private int option16 = -1;
    private int option17 = -1;
    private int option18 = -1;
    private int option19 = -1;
    private int option20 = -1;

    private String text0 = "";
    private String text1 = "";
    private String text2 = "";
    private String text3 = "";
    private String text4 = "";
    private String text5 = "";
    private String text6 = "";
    private String text7 = "";
    private String text8 = "";
    private String text9 = "";
    private String text10 = "";
    private String text11 = "";
    private String text12 = "";
    private String text13 = "";
    private String text14 = "";
    private String text15 = "";
    private String text16 = "";
    private String text17 = "";
    private String text18 = "";
    private String text19 = "";
    private String text20 = "";

    private String longestText = "hello";
    private String secondLongestText = "hello";





    public void setOptionAt(int page, int choice) {
        switch (page) {
            case 0:
                setOption0(choice);
                break;
            case 1:
                setOption1(choice);
                break;
            case 2:
                setOption2(choice);
                break;
            case 3:
                setOption3(choice);
                break;
            case 4:
                setOption4(choice);
                break;
            case 5:
                setOption5(choice);
                break;
            case 6:
                setOption6(choice);
                break;
            case 7:
                setOption7(choice);
                break;
            case 8:
                setOption8(choice);
                break;
            case 9:
                setOption9(choice);
                break;
            case 10:
                setOption10(choice);
                break;
            case 11:
                setOption11(choice);
                break;
            case 12:
                setOption12(choice);
                break;
            case 13:
                setOption13(choice);
                break;
            case 14:
                setOption14(choice);
                break;
            case 15:
                setOption15(choice);
                break;
            case 16:
                setOption16(choice);
                break;
            case 17:
                setOption17(choice);
                break;
            case 18:
                setOption18(choice);
                break;
            case 19:
                setOption19(choice);
                break;
            case 20:
                setOption20(choice);
                break;
        }


    }

    public void setTextAt(int page, String text) {
        switch (page) {
            case 0:
                setText0(text);
                break;
            case 1:
                setText1(text);
                break;
            case 2:
                setText2(text);
                break;
            case 3:
                setText3(text);
                break;
            case 4:
                setText4(text);
                break;
            case 5:
                setText5(text);
                break;
            case 6:
                setText6(text);
                break;
            case 7:
                setText7(text);
                break;
            case 8:
                setText8(text);
                break;
            case 9:
                setText9(text);
                break;
            case 10:
                setText10(text);
                break;
            case 11:
                setText11(text);
                break;
            case 12:
                setText12(text);
                break;
            case 13:
                setText13(text);
                break;
            case 14:
                setText14(text);
                break;
            case 15:
                setText15(text);
                break;
            case 16:
                setText16(text);
                break;
            case 17:
                setText17(text);
                break;
            case 18:
                setText18(text);
                break;
            case 19:
                setText19(text);
                break;
            case 20:
                setText20(text);
                break;
        }
    }
    public int getOptionAt(int page) {
        switch (page) {
            case 0:
                return getOption0();
            case 1:
                return getOption1();
            case 2:
                return getOption2();
            case 3:
                return getOption3();
            case 4:
                return getOption4();
            case 5:
                return getOption5();
            case 6:
                return getOption6();
            case 7:
                return getOption7();
            case 8:
                return getOption8();
            case 9:
                return getOption9();
            case 10:
                return getOption10();
            case 11:
                return getOption11();
            case 12:
                return getOption12();
            case 13:
                return getOption13();
            case 14:
                return getOption14();
            case 15:
                return getOption15();
            case 16:
                return getOption16();
            case 17:
                return getOption17();
            case 18:
                return getOption18();
            case 19:
                return getOption19();
            case 20:
                return getOption20();
        }
        return getOption0();
    }
    public String getTextAt(int page) {
        switch (page) {
            case 0:
                return getText0();
            case 1:
                return getText1();
            case 2:
                return getText2();
            case 3:
                return getText3();
            case 4:
                return getText4();
            case 5:
                return getText5();
            case 6:
                return getText6();
            case 7:
                return getText7();
            case 8:
                return getText8();
            case 9:
                return getText9();
            case 10:
                return getText10();
            case 11:
                return getText11();
            case 12:
                return getText12();
            case 13:
                return getText13();
            case 14:
                return getText14();
            case 15:
                return getText15();
            case 16:
                return getText16();
            case 17:
                return getText17();
            case 18:
                return getText18();
            case 19:
                return getText19();
            case 20:
                return getText20();
        }
        return getText0();

    }

    public void setLongestTexts() {
        longestText = "";
        secondLongestText = "";
        String[] texts = {text0, text1, text2, text3, text4, text5, text6, text7, text8, text9, text10,
                text11, text12, text13, text14, text15, text16, text17, text18, text19, text20};
        for (String t: texts) {
            if (t != null) {
                if (t.length() > longestText.length()) {
                    secondLongestText = longestText;
                    longestText = t;
                } else if (t.length() > secondLongestText.length()) {
                    secondLongestText = t;
                }
            }

        }

    }



    public Date getJavaDateCreated() {
        return new Date(dateCreated);
    }

    public void setJavaDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated.getTime();
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getLongestText() {
        return longestText;
    }

    public void setLongestText(String longestText) {
        this.longestText = longestText;
    }

    public String getSecondLongestText() {
        return secondLongestText;
    }

    public void setSecondLongestText(String secondLongestText) {
        this.secondLongestText = secondLongestText;
    }






    public int getOption0() {
        return option0;
    }

    public void setOption0(int option0) {
        this.option0 = option0;
    }

    public int getOption1() {
        return option1;
    }

    public void setOption1(int option1) {
        this.option1 = option1;
    }

    public int getOption2() {
        return option2;
    }

    public void setOption2(int option2) {
        this.option2 = option2;
    }

    public int getOption3() {
        return option3;
    }

    public void setOption3(int option3) {
        this.option3 = option3;
    }

    public int getOption4() {
        return option4;
    }

    public void setOption4(int option4) {
        this.option4 = option4;
    }

    public int getOption5() {
        return option5;
    }

    public void setOption5(int option5) {
        this.option5 = option5;
    }

    public int getOption6() {
        return option6;
    }

    public void setOption6(int option6) {
        this.option6 = option6;
    }

    public int getOption7() {
        return option7;
    }

    public void setOption7(int option7) {
        this.option7 = option7;
    }

    public int getOption8() {
        return option8;
    }

    public void setOption8(int option8) {
        this.option8 = option8;
    }

    public int getOption9() {
        return option9;
    }

    public void setOption9(int option9) {
        this.option9 = option9;
    }

    public int getOption10() {
        return option10;
    }

    public void setOption10(int option10) {
        this.option10 = option10;
    }

    public int getOption11() {
        return option11;
    }

    public void setOption11(int option11) {
        this.option11 = option11;
    }

    public int getOption12() {
        return option12;
    }

    public void setOption12(int option12) {
        this.option12 = option12;
    }

    public int getOption13() {
        return option13;
    }

    public void setOption13(int option13) {
        this.option13 = option13;
    }

    public int getOption14() {
        return option14;
    }

    public void setOption14(int option14) {
        this.option14 = option14;
    }

    public int getOption15() {
        return option15;
    }

    public void setOption15(int option15) {
        this.option15 = option15;
    }

    public int getOption16() {
        return option16;
    }

    public void setOption16(int option16) {
        this.option16 = option16;
    }

    public int getOption17() {
        return option17;
    }

    public void setOption17(int option17) {
        this.option17 = option17;
    }

    public int getOption18() {
        return option18;
    }

    public void setOption18(int option18) {
        this.option18 = option18;
    }

    public int getOption19() {
        return option19;
    }

    public void setOption19(int option19) {
        this.option19 = option19;
    }

    public int getOption20() {
        return option20;
    }

    public void setOption20(int option20) {
        this.option20 = option20;
    }

    public String getText0() {
        return text0;
    }

    public void setText0(String text0) {
        this.text0 = text0;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    public String getText5() {
        return text5;
    }

    public void setText5(String text5) {
        this.text5 = text5;
    }

    public String getText6() {
        return text6;
    }

    public void setText6(String text6) {
        this.text6 = text6;
    }

    public String getText7() {
        return text7;
    }

    public void setText7(String text7) {
        this.text7 = text7;
    }

    public String getText8() {
        return text8;
    }

    public void setText8(String text8) {
        this.text8 = text8;
    }

    public String getText9() {
        return text9;
    }

    public void setText9(String text9) {
        this.text9 = text9;
    }

    public String getText10() {
        return text10;
    }

    public void setText10(String text10) {
        this.text10 = text10;
    }

    public String getText11() {
        return text11;
    }

    public void setText11(String text11) {
        this.text11 = text11;
    }

    public String getText12() {
        return text12;
    }

    public void setText12(String text12) {
        this.text12 = text12;
    }

    public String getText13() {
        return text13;
    }

    public void setText13(String text13) {
        this.text13 = text13;
    }

    public String getText14() {
        return text14;
    }

    public void setText14(String text14) {
        this.text14 = text14;
    }

    public String getText15() {
        return text15;
    }

    public void setText15(String text15) {
        this.text15 = text15;
    }

    public String getText16() {
        return text16;
    }

    public void setText16(String text16) {
        this.text16 = text16;
    }

    public String getText17() {
        return text17;
    }

    public void setText17(String text17) {
        this.text17 = text17;
    }

    public String getText18() {
        return text18;
    }

    public void setText18(String text18) {
        this.text18 = text18;
    }

    public String getText19() {
        return text19;
    }

    public void setText19(String text19) {
        this.text19 = text19;
    }

    public String getText20() {
        return text20;
    }

    public void setText20(String text20) {
        this.text20 = text20;
    }







    @Override
    public String toString() {

        return "Assessment belonging to: " + userEmail + "\n"
                + "with objectID: " + objectId + "\n"
                + "with roomID: " + roomId + "\n"
                + "dateCreated on: " + dateCreated + "\n"
                + "with longest text: " + longestText + "\n"
                + "with second longest text: " + secondLongestText + "\n"
                + "with text0: " + text0 + "\n"
                + "with text1: " + text1 + "\n"
                + "with text2: " + text2 + "\n"
                + "with text3: " + text3 + "\n"
                + "with text4: " + text4 + "\n"
                + "with text5: " + text5 + "\n"
                + "with text6: " + text6 + "\n"
                + "with text7: " + text7 + "\n"
                + "with text8: " + text8 + "\n"
                + "with text9: " + text9 + "\n"
                + "with option0: " + option0 + "\n"
                + "with option1: " + option1 + "\n"
                + "with option2: " + option2 + "\n"
                + "with option3: " + option3 + "\n"
                + "with option4: " + option4 + "\n"
                + "with option5: " + option5 + "\n"
                + "with option6: " + option6 + "\n"
                + "with option7: " + option7 + "\n"
                + "with option8: " + option8 + "\n";
    }
}
