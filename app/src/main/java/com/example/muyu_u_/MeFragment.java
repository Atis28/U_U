package com.example.muyu_u_;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//
//import com.example.muyu_u_.adapter.NotesAdapter;
//import com.example.muyu_u_.database.DatabaseHelper;
//import com.example.muyu_u_.model.Note;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeFragment extends Fragment {

//
//    private NotesAdapter mAdapter;
//    private List<Note> notesList = new ArrayList<>();
//
//    private RecyclerView recyclerView;
//
//    private DatabaseHelper db;

    CircleImageView circleImageView;
    Button btnAddImg, btnQuotes;
    EditText edQuotes;

    public static final int IMAGE_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        recyclerView = view.findViewById(R.id.recycler_view);
//        db = new DatabaseHelper(requireContext());
//
//        notesList.addAll(db.getAllNotes());
//
//        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showNoteDialog(false, null, -1);
//            }
//        });
//        mAdapter = new NotesAdapter(this, notesList);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
//        recyclerView.setAdapter(mAdapter);
//
//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(requireContext(), recyclerView, new RecyclerTouchListener.ClickListener()  {
//            @Override
//            public void onClick(View view, final int position) {
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                showActionsDialog(position);
//            }
//        }));


        //avatar
        circleImageView = view.findViewById(R.id.profile_image);
        btnAddImg = view.findViewById(R.id.add_img);
        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImgForm();
            }
        });

        //quotes
        btnQuotes = view.findViewById(R.id.add_quotes);
        edQuotes = view.findViewById(R.id.ed_quotes);
        SharedPreferences pref_quotes = getActivity().getSharedPreferences("quotes", Context.MODE_PRIVATE);
        final String[] savedText = {pref_quotes.getString("text_key", "")};
        edQuotes.setText(savedText[0]); // set saved text to EditText

        btnQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edQuotes.getText().toString();
                savedText[0] = text; // update savedText with the new value of text
                Toast.makeText(getActivity(), "oke", Toast.LENGTH_LONG).show();
            }
        });

    }
//
//    //Inserting new note in db
//    private void createNote(String note) {
//        // inserting note in db and getting
//        // newly inserted note id
//        long id = db.insertNote(note);
//
//        // get the newly inserted note from db
//        Note n = db.getNote(id);
//
//        if (n != null) {
//            // adding new note to array list at 0 position
//            notesList.add(0, n);
//
//            // refreshing the list
//            mAdapter.notifyDataSetChanged();
//
//        }
//    }
//
//    private void updateNote(String note, int position) {
//        Note n = notesList.get(position);
//        // updating note text
//        n.setNote(note);
//
//        // updating note in db
//        db.updateNote(n);
//
//        // refreshing the list
//        notesList.set(position, n);
//        mAdapter.notifyItemChanged(position);
//
//    }
//
//    private void deleteNote(int position) {
//        // deleting the note from db
//        db.deleteNote(notesList.get(position));
//
//        // removing the note from the list
//        notesList.remove(position);
//        mAdapter.notifyItemRemoved(position);
//
//    }
//
//
//    private void showActionsDialog(final int position) {
//        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
//        builder.setTitle("Choose option");
//        builder.setItems(colors, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0) {
//                    showNoteDialog(true, notesList.get(position), position);
//                } else {
//                    deleteNote(position);
//                }
//            }
//        });
//        builder.show();
//    }
//
//
//    private void showNoteDialog(final boolean shouldUpdate, final Note note, final int position) {
//        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
//        View view = layoutInflaterAndroid.inflate(R.layout.note_dialog, null);
//        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(requireContext());
//        alertDialogBuilderUserInput.setView(view);
//
//        final EditText inputNote = view.findViewById(R.id.note);
//
//        if (shouldUpdate && note != null) {
//            inputNote.setText(note.getNote());
//        }
//        alertDialogBuilderUserInput
//                .setCancelable(false)
//                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialogBox, int id) {
//
//                    }
//                })
//                .setNegativeButton("cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialogBox, int id) {
//                                dialogBox.cancel();
//                            }
//                        });
//
//        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
//        alertDialog.show();
//
//        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Show toast message when no text is entered
//                if (TextUtils.isEmpty(inputNote.getText().toString())) {
//                    Toast.makeText(getActivity(), "Enter note!", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                    alertDialog.dismiss();
//                }
//
//                // check if user updating note
//                if (shouldUpdate && note != null) {
//                    // update note by it's id
//                    updateNote(inputNote.getText().toString(), position);
//                } else {
//                    // create new note
//                    createNote(inputNote.getText().toString());
//                }
//            }
//        });
//    }


    public void openImgForm(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_CODE);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_CODE);

    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == IMAGE_CODE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    circleImageView.setImageURI(selectedImageUri);
                }
            }
        }
    }

}