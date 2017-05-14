package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Generator {

    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String OUT_DIR = PROJECT_DIR + "/app/src/main/java";

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(1, "greendao.ipc.am.ipc_greendao.dao");

        addTables(schema);

        new DaoGenerator().generateAll(schema, OUT_DIR);
    }

    private static void addTables(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty().primaryKey().autoincrement();
        note.addStringProperty("text").notNull();
    }
}
