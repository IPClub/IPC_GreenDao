package greendao.ipc.am.ipc_greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import greendao.ipc.am.ipc_greendao.dao.Note;
import greendao.ipc.am.ipc_greendao.dao.NoteDao;

public class MainActivity extends AppCompatActivity {

    NoteDao noteDao;
    EditText et;
    ListView lv;
    ArrayAdapter<Note> adapter;
    List<Note> allNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteDao = App.getInstance().daoSession().getNoteDao();
        et = (EditText) findViewById(R.id.text);
        lv = (ListView) findViewById(R.id.lv);
        allNotes = noteDao.loadAll();

        adapter = new ArrayAdapter<Note>(this,android.R.layout.simple_list_item_1,allNotes);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               deleteNote(position);
            }
        });
    }

    private void deleteNote(int position) {
        noteDao.delete(allNotes.get(position));
        allNotes.remove(position);
        adapter.notifyDataSetChanged();
    }

    public void addNote(View view) {
        Note note = new Note();
        note.setText(et.getText().toString());
        noteDao.insert(note);
        allNotes.add(note);
        adapter.notifyDataSetChanged();
    }


}
