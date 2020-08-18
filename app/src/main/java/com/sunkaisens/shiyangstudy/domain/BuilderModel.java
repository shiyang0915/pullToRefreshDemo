package com.sunkaisens.shiyangstudy.domain;

/**
 * @author:shiyang
 * @date:2019-12-24
 * @email:shiyang@sunkaisens.com
 * @Description:
 */
public class BuilderModel {

    /**
     * 必选字段
     */
    private final String name;

    /**
     * 必选字段
     */
    private final int age;

    private final char sex;


    private final String hobby;


    private BuilderModel(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
        this.hobby = builder.hobby;
    }


    public static class Builder {

        private final String name;

        private final int age;

        private char sex;

        private String hobby;


        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }


        public Builder setSex(char sex) {
            this.sex = sex;
            return this;
        }

        public Builder setHobby(String hobby) {
            this.hobby = hobby;
            return this;
        }


        public BuilderModel build() {
            return new BuilderModel(this);
        }


    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public String getHobby() {
        return hobby;
    }


    public static void main(String[] args) {
        BuilderModel builderModel = new BuilderModel
                .Builder("施洋", 28)
                .setSex('男')
                .setHobby("篮球")
                .build();

        System.out.println(builderModel.getName());
        System.out.println(builderModel.getAge());
        System.out.println(builderModel.getSex());
        System.out.println(builderModel.getHobby());

    }


}
