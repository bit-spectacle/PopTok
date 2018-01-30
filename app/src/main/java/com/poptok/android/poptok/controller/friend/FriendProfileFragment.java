//package com.poptok.android.poptok.controller.friend;
//
//import android.app.Fragment;
//import android.widget.TextView;
//
//import com.poptok.android.poptok.R;
//import com.poptok.android.poptok.model.auth.AuthStore;
//import com.poptok.android.poptok.model.post.PostListItem;
//import com.poptok.android.poptok.service.IAsyncResultHandler;
//
//import org.androidannotations.annotations.Bean;
//import org.androidannotations.annotations.EFragment;
//import org.androidannotations.annotations.ViewById;
//
//import java.util.List;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
///**
// * Created by BIT on 2018-01-23.
// */
//@EFragment
//public class FriendProfileFragment extends Fragment implements IAsyncResultHandler<List<PostListItem>> {
//
//
//    @ViewById(R.id.profileImage)
//    CircleImageView profileImage;
//
//    @ViewById(R.id.friendNameTextView)
//    TextView friendNameTextView;
//
//    @ViewById(R.id.friendStatusTextView)
//    TextView friendStatusTextView;
//
//    @Bean
//    AuthStore authStore;
//
//    String LOG_TAG = "FriendProfileFragment : ";
//
//
//
//    public FriendProfileFragment(){
//
//
//    }
//
//    @Override
//    public void resultHandler(List<PostListItem> result) {
//
//    }
//}
