package com.adamwilson.common.api;

public class ImageSize {

    public static final int CROPPED_70x70px = 1;
    public static final int CROPPED_px140   = 2;
    public static final int CROPPED_px280   = 3;
    public static final int CROPPED_px100   = 100;
    public static final int CROPPED_px200   = 200;
    public static final int CROPPED_px440   = 440;
    public static final int CROPPED_px600   = 600;

    public static final int UNCROPPED_LONGEST_256px  = 30;
    public static final int UNCROPPED_LONGEST_900px  = 4;
    public static final int UNCROPPED_LONGEST_1080px = 1080;
    public static final int UNCROPPED_LONGEST_1170px = 5;
    public static final int UNCROPPED_LONGEST_1600px = 1600;
    public static final int UNCROPPED_LONGEST_2048px = 2048;

    public static final int UNCROPPED_HEIGHT_300px = 20;
    public static final int UNCROPPED_HEIGHT_600px = 21;

    static public int[] getAll() {
        return new int[]{
                CROPPED_70x70px,
                CROPPED_px140,
                CROPPED_px280,
                CROPPED_px100,
                CROPPED_px200,
                CROPPED_px440,
                CROPPED_px600,
                UNCROPPED_LONGEST_256px,
                UNCROPPED_LONGEST_900px,
                UNCROPPED_LONGEST_1080px,
                UNCROPPED_LONGEST_1170px,
                UNCROPPED_LONGEST_1600px,
                UNCROPPED_LONGEST_2048px,
                UNCROPPED_HEIGHT_300px,
                UNCROPPED_HEIGHT_600px
        };
    }

}
