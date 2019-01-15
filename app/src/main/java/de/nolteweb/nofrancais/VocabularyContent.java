package de.nolteweb.nofrancais;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class VocabularyContent {

    /**
     * An array of vocabulary items.
     */
    static List<VocabularyContent.VocabularyItem> items;

    /**
     * A map of vocabulary items, by ID.
     */
    public static final Map<String, VocabularyItem> ITEM_MAP = new HashMap<String, VocabularyItem>();

    private static final String FILENAME = "vocs.csv";

    public static List<VocabularyContent.VocabularyItem> getItems(Context applicationContext) {
        if(items == null){
            items = readItems(applicationContext);
        }
        return items;
    }

    private static void addItem(VocabularyItem item) {
        items.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class VocabularyItem {
        public String id;
        public String vocabulary;
        public  String translation;
        public String clipFile;

        public VocabularyItem(String id, String vocabulary, String translation, String clipFile) {
            this.id = id;
            this.vocabulary = vocabulary;
            this.translation = translation;
            this.clipFile = clipFile;
        }

        @Override
        public String toString() {
            return vocabulary;
        }
    }

    private static List<VocabularyItem> readItems(Context applicationContext) {
        List<VocabularyItem> vocList = new ArrayList<VocabularyItem>();
        try {
            File rf = new File(applicationContext.getDataDir(),FILENAME);
            AssetManager assManager = applicationContext.getAssets();
            InputStream is = assManager.open(FILENAME);
            System.out.println("File Localtion:" + rf.getAbsolutePath());
            String rp = applicationContext.getPackageResourcePath();
            System.out.println("Resource Path:" + rp);
            BufferedReader br  = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = null;

            Scanner scanner = null;
            int index = 0;

            while((line = br.readLine()) != null){
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                String id = null;
                String vocabulary = null;
                String translation = null;
                String clipFile = null;
                if(scanner.hasNext()){
                    id = scanner.next();
                    vocabulary = scanner.next();
                    translation = scanner.next();
                    clipFile = scanner.next();
                }
                VocabularyItem item = new VocabularyItem(id, vocabulary, translation, clipFile);
                vocList.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vocList;
    }
}
