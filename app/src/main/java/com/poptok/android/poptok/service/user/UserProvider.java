package com.poptok.android.poptok.service.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.GsonBuilder;
import com.poptok.android.poptok.exception.HttpResponseException;
import com.poptok.android.poptok.exception.JSONResultException;
import com.poptok.android.poptok.model.JSONResult;
import com.poptok.android.poptok.model.user.UserInfo;
import com.poptok.android.poptok.service.Config;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;



public class UserProvider {


    private Context context;

    public UserProvider(Context context){
        this.context = context;
    }

    public UserInfo login(String email, String password) throws HttpResponseException, JSONResultException {

        String url = Config.apiUrl + "/auth/login/"+email + "/" + password;
//        String url = "http://192.168.1.39:3000/auth/login/";
//        String query = "/" + email + "/" + password;


        HttpRequest request = HttpRequest.get( url );
        request.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");

        request.accept( HttpRequest.CONTENT_TYPE_JSON );
        request.connectTimeout( 1000 );
        request.readTimeout( 3000 );
//        request.send( query );

        int responseCode = request.code();
        if ( responseCode != HttpURLConnection.HTTP_OK  ) {
            throw new HttpResponseException( "Response Code:" + responseCode  );
        }

        JSONResultLogin result = new GsonBuilder().
                create().
                fromJson(
                        request.bufferedReader(),
                        JSONResultLogin.class );
        if( "FAIL".equals( result.getCode() ) == true ) {
            throw new JSONResultException( result.getMessage() );
        }

        // 로긴 성공( JSESSIONID Cookie 저장)
        String jsessionCookie = null;
        Map<String, List<String>> map = request.getConnection().getHeaderFields();        if( map != null ) {
            List<String> cookies = map.get( "Set-Cookie" );
            if( cookies != null ) {
                for( String cookieString : cookies ) {
                    if( cookieString.startsWith( "connect.sid=" )){
                        jsessionCookie =
                                cookieString.substring( 0, cookieString.indexOf( ";" ) );
                    }
                }
            }
        }

        System.out.println( "----------->" + jsessionCookie );

        if( jsessionCookie != null ) {
            SharedPreferences sharedPreference = context.getSharedPreferences( "poptok", MODE_PRIVATE );
            SharedPreferences.Editor sharedPreferenceEditor = sharedPreference.edit();
            sharedPreferenceEditor.putString( "session", jsessionCookie );
            sharedPreferenceEditor.commit();
        }

        return result.getData();

    }

    public List<UserInfo> fetchUserList() throws HttpResponseException, JSONResultException {
        String url = Config.apiUrl + "/posting/list/0";
        HttpRequest request = HttpRequest.get( url );

        // session 얻어오기
        SharedPreferences preferences = context.getSharedPreferences( "poptok", Context.MODE_PRIVATE );
        String sessionCookies = preferences.getString( "session", null );
        String cookieString = "";
        if( sessionCookies != null ) {
            cookieString = cookieString + ";" + sessionCookies;
        }

        // cookie 세팅
        request.header( "Cookie", cookieString );

        request.contentType( HttpRequest.CONTENT_TYPE_JSON );
        request.accept( HttpRequest.CONTENT_TYPE_JSON );
        request.connectTimeout( 1000 );
        request.readTimeout( 3000 );

        int responseCode = request.code();
        if ( responseCode != HttpURLConnection.HTTP_OK  ) {
            throw new HttpResponseException( "Response Code:" + responseCode  );
        }

        JSONResultFetchUserList result = new GsonBuilder().
                create().
                fromJson(
                        request.bufferedReader(),
                        JSONResultFetchUserList.class );
        if( "FAIL".equals( result.getCode() ) == true ) {
            throw new JSONResultException( result.getMessage() );
        }

        return result.getData();
    }

    private class JSONResultLogin extends JSONResult<UserInfo> {}
    private class JSONResultFetchUserList extends JSONResult<List<UserInfo>> {}

}
//public class UserProvider implements IUserFinder {
//    @Override
//    public List<UserInfo> findUser(String email) {
//        List<UserInfo> user = null;
//        return user;
//    }
//
//    @Override
//    public List<UserInfo> userLogin(String email, String password) {
//
//        List<UserInfo> user = null;
//        String url = "http://192.168.1.39:3000";
//
//
//
//        return user;
//    }
//
//    @Override
//    public List<UserInfo> userJoin(String email, String password, String nickname) {
//        List<UserInfo> user = null;
//        return user;
//    }
//
//    @Override
//    public List<UserInfo> userLogout(int userNo) {
//        List<UserInfo> user = null;
//        return user;
//    }
//}
