package com.youplay.reservation.side_api;

import java.util.ArrayList;
import java.util.List;



public class GizmoApi {

    public static List<PC> getPcList() {
        List<PC> pcList = new ArrayList<>();

        // Создание объектов PC с данными из примера
        pcList.add(new PC(5, "18"));
        pcList.add(new PC(6, "20"));
        pcList.add(new PC(7, "19"));
        pcList.add(new PC(8, "17"));
        pcList.add(new PC(9, "11"));
        pcList.add(new PC(10, "12"));
        pcList.add(new PC(11, "13"));
        pcList.add(new PC(12, "14"));
        pcList.add(new PC(13, "15"));
        pcList.add(new PC(14, "16"));
        pcList.add(new PC(15, "6"));
        pcList.add(new PC(16, "7"));
        pcList.add(new PC(17, "5"));
        pcList.add(new PC(18, "9"));
        pcList.add(new PC(19, "10"));
        pcList.add(new PC(20, "1"));
        pcList.add(new PC(21, "3"));
        pcList.add(new PC(22, "8"));
        pcList.add(new PC(23, "4"));
        pcList.add(new PC(24, "2"));
        pcList.add(new PC(25, "PS4 - VIP"));
        pcList.add(new PC(26, "PS4 - Диван (Слева)"));
        pcList.add(new PC(27, "PS4 - Диван (Справа)"));
        pcList.add(new PC(28, "22"));
        pcList.add(new PC(29, "21"));
        pcList.add(new PC(40, "24"));
        pcList.add(new PC(41, "23"));

        return pcList;
    }

    public static class PC {
        private int id;
        private String name;

        public PC(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
