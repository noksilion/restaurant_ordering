package com.netcracker.kasianova.utils;

import java.io.File;

public enum Constants {
    INSTANCE;
    public static final String  fileName =  "SavedRestaurant.bin";

    public static final String  filePath ="com/netcracker/kasianova/dao" + File.separator+ "fileDAO"
            +File.separator ;

    public String getFileName()
    {
        return fileName;
    }

    public String getFilePath ()
    {
        return filePath;
    }

}
