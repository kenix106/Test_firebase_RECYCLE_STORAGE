package cl.evilgenius.test_firebase_recycle_storage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewNoteActivity extends AppCompatActivity {


    private EditText editTextTittle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Agregar Nota");

        editTextTittle = findViewById(R.id.edit_text_tittle);
        editTextDescription = findViewById(R.id.edit_text_Description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.save_note:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

        // return super.onOptionsItemSelected(item);
    }


    private void saveNote(){
        //aqui guardamos los datos en la DB

        String tittle = editTextTittle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (tittle.trim().isEmpty()||description.trim().isEmpty()){
            Toast.makeText(this, "Favor de introducir un Titulo y Descripcion", Toast.LENGTH_SHORT).show();
        return;
        }
        CollectionReference notebookRef = FirebaseFirestore.getInstance().collection("Notebook");
        notebookRef.add(new Note(tittle, description, priority));
        Toast.makeText(this, "Nota Guardada", Toast.LENGTH_SHORT).show();
        finish();
    }
}
