package com.example.muyu_u_;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.muyu_u_.adapter.MusicAdapter;
import com.example.muyu_u_.model.Music;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private List<Music> musicList;
    private FirebaseFirestore firestore;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        musicList = new ArrayList<>();
//        musicAdapter = new MusicAdapter(musicList, new MusicAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Music music) {
//                // Xử lý khi người dùng nhấp vào một bài hát
//                // Ví dụ: Phát nhạc từ đường dẫn URL
//                String musicUrl = music.getUrl();
//                // Thực hiện phát nhạc từ đường dẫn musicUrl
//            }
//        });
//
//        recyclerView.setAdapter(musicAdapter);
//
//        //Tạo một tham chiếu đến Firestore
//        firestore = FirebaseFirestore.getInstance();
//        databaseRef = FirebaseDatabase.getInstance().getReference("music");
//
//        //Lấy tham chiếu đến collection chứa dữ liệu âm nhạc trong Firestore
//        CollectionReference musicCollection = firestore.collection("music");
//
//        musicCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                if (error != null) {
//                    Log.e(TAG, "Lỗi khi lấy dữ liệu từ Firestore: ", error);
//                    return;
//                }
//                musicList.clear();
//                for (DocumentSnapshot document : value) {
//                    String title = document.getString("title");
//                    String bio = document.getString("bio");
//                    String time = document.getString("time");
//                    String url = document.getString("url");
//                    Music music = new Music(title, bio, time, url);
//                    musicList.add(music);
//                }
//                musicAdapter.notifyDataSetChanged();
//            }
//        });

    }

    private void initView(){
        recyclerView = findViewById(R.id.recycler_View);
    }
}