package com.yato.direcrory.entity;

public enum NumberType {
        MOBILE("📱 Мобильный"),
        HOME("🏠 Домашний"),
        WORK("💼 Рабочий"),
        FAX("📠 Факс");

        private final String phoneType;

        NumberType(String phoneType) {
            this.phoneType = phoneType;
        }

        public String getPhoneType() {
            return phoneType;
        }
    }
