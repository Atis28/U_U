package com.example.muyu_u_;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.muyu_u_.adapter.MusicAdapter;
import com.example.muyu_u_.model.Music;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private List<Music> musicList;
    private FirebaseFirestore firestore;
    DatabaseReference databaseRef;
    FirebaseStorage storage;
    StorageReference storageRef;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        initView();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        musicList = new ArrayList<>();
        musicAdapter = new MusicAdapter(musicList, new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Music music) {
                // Xử lý khi người dùng nhấp vào một bài hát
                String musicUrl = music.getUrl();
                playMusic(musicUrl);
            }
        });

        recyclerView.setAdapter(musicAdapter);

        //tao tham chieu storage
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("gs://muyu-76b15.appspot.com/");

        //Tạo một tham chiếu đến Firestore
        firestore = FirebaseFirestore.getInstance();
        databaseRef = FirebaseDatabase.getInstance("https://muyu-76b15-default-rtdb.firebaseio.com").getReference();

//        //save title, time, url
//        storageRef.listAll()
//                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
//                    @Override
//                    public void onSuccess(ListResult listResult) {
//                        databaseRef = FirebaseDatabase.getInstance("https://muyu-76b15-default-rtdb.firebaseio.com").getReference();
//                        for (StorageReference item : listResult.getItems()) {
//                            String title = item.getName();
//                            item.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
//                                @Override
//                                public void onSuccess(StorageMetadata storageMetadata) {
//                                    long time = storageMetadata.getCreationTimeMillis();
//                                    item.getDownloadUrl()
//                                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                                @Override
//                                                public void onSuccess(Uri uri) {
//                                                    String url = uri.toString();
//                                                    // Create a Song object and store the data in Realtime Database
//                                                    Music music = new Music(title, time, url);
//                                                    databaseRef.push().setValue(music);
//                                                }
//                                            })
//                                            .addOnFailureListener(new OnFailureListener() {
//                                                @Override
//                                                public void onFailure(@NonNull Exception exception) {
//                                                    // Handle the error when unable to retrieve the download URL of the music file
//                                                }
//                                            });
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception exception) {
//                                    // Handle the error when unable to retrieve the metadata of the storage file
//                                }
//                            });
//                        }
//                    }
//                });

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (error != null) {
//                    Log.e(TAG, "Lỗi khi lấy dữ liệu từ Firestore: ", error);
//                    return;
//                }
                musicList.clear();
                for (DataSnapshot document : snapshot.getChildren()) {
                    Music music = document.getValue(Music.class);
                    musicList.add(music);
                }
                musicAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void playMusic(String musicUrl) {
        // Kiểm tra nếu mediaPlayer đang phát nhạc từ URL khác, ta dừng và giải phóng mediaPlayer
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(musicUrl);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // Khi mediaPlayer đã sẵn sàng, ta bắt đầu phát nhạc
                    mediaPlayer.start();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Giải phóng mediaPlayer khi Activity bị hủy
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void initView(){
        recyclerView = findViewById(R.id.recycler_View);
    }
}