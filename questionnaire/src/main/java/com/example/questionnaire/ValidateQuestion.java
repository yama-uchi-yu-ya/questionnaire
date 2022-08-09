package com.example.questionnaire;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ValidateQuestion {

    @NotEmpty(message = "一つ選択してください")
    private Integer like_meat;

    @NotBlank(message = "一つ以上選択してください")
    private Integer like_veg;

    @NotBlank(message = "何か入力してください")
    @Pattern(regexp = "^[^ -~｡-ﾟ]+$")
    private String like_idol;

    public Integer getLike_meat() {
        return this.like_meat;
    }

    public void setLike_meat(Integer like_meat) {
        this.like_meat = like_meat;
    }

    public Integer getLike_veg() {
        return this.like_veg;
    }

    public void setLike_veg(Integer like_veg) {
        this.like_veg = like_veg;
    }

    public String getLike_idol() {
        return this.like_idol;
    }

    public void setLike_idol(String like_idol) {
        this.like_idol = like_idol;
    }
}
