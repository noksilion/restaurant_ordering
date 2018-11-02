package com.netcracker.kasianova.dao.fileDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.netcracker.kasianova.entities.Restaurant;
import com.netcracker.kasianova.utils.Constants;

public enum  RestaurantBinaryFileDAO  {
    INSTANCE;

    Constants constants =Constants.INSTANCE;

    public void saveRestaurant(Object object) {
        FileOutputStream f = null;
        ObjectOutputStream o = null;

        try {

            f = new FileOutputStream(new File("src" + File.separator+"main"+ File.separator
                    +"java"+ File.separator +constants.getFilePath() + constants.getFileName() ));

            o = new ObjectOutputStream(f);

            o.writeObject(object);

            o.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
        finally
        {
            try {
                if (f != null)
                    f.close();

                if (o != null)
                    o.close();
            }
            catch (IOException ignored)
            { }
        }
    }

    public Restaurant getObject() {
        Restaurant restaurant = null;
        FileInputStream fi =null ;
        ObjectInputStream oi =null;

        try {

            fi = new FileInputStream(new File("src" + File.separator+"main"+ File.separator
                    +"java"+File.separator + constants.getFilePath() + constants.getFileName()));

            oi = new ObjectInputStream(fi);

            restaurant = (Restaurant) oi.readObject();

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                if (fi != null)
                    fi.close();
            }
            catch (IOException ignored) { }
            try {
                if (oi != null)
                    oi.close();
            }
            catch (IOException ignored) { }
        }

        return restaurant;
    }

    public boolean fileExists(String filePath)
    {
        File file = new File(filePath);
        return file.exists();
    }
}

