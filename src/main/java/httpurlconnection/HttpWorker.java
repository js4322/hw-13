package httpurlconnection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jsonplaceholder_typicode_com.data_structures.Comment;
import jsonplaceholder_typicode_com.data_structures.Post;
import jsonplaceholder_typicode_com.data_structures.Todo;
import jsonplaceholder_typicode_com.data_structures.User;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static jsonplaceholder_typicode_com.sources.PathUtils.SOURCE_POSTS;
import static jsonplaceholder_typicode_com.sources.PathUtils.SOURCE_USERS;

public class HttpWorker {
    private static HttpURLConnection connection;
    public static void userAdd() throws IOException, IllegalAccessException {
        System.out.println("userAdd method using HttpURLConnection");
        Gson gson = new Gson();
        connection = (HttpURLConnection) new URL(SOURCE_USERS).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        out.append(gson.toJson(new User()));
        out.flush();
        out.close();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_CREATED){
            Reader in = new InputStreamReader(connection.getInputStream());
            System.out.println("Response is " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
            System.out.println("httpResponse object and newly created object that is created similarly");
            User.showDifference(gson.fromJson(in, User.class), new User());
            in.close();
            }
        else
            System.out.println("Response is not ok: " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
        connection.disconnect();
        System.out.println("---End of userAdd method using HttpURLConnection\n\n");
    }
    public static void userUpdate() throws IOException, IllegalAccessException {
        System.out.println("userUpdate method using HttpURLConnection");
        Gson gson = new Gson();
        connection = (HttpURLConnection) new URL(SOURCE_USERS + "/1").openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        out.append(gson.toJson(new User()));
        out.close();
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            System.out.println("Response is " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
            Reader in = new InputStreamReader(connection.getInputStream());
            System.out.println("provided and returned objects:");
            System.out.println("response from server and newly created user");
            User.showDifference(gson.fromJson(in,User.class), new User());
        }
        else
            System.out.println("Response is not ok: " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
        connection.disconnect();
        System.out.println("---End of userUpdate method using HttpURLConnection\n\n");
    }
    public static void removeUserByID(int id) throws IOException, IllegalAccessException {
        System.out.println("removeUserByID method using HttpURLConnection");
        HttpURLConnection connection = (HttpURLConnection) new URL(SOURCE_USERS + "/" + id).openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            System.out.println("Response is " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
            System.out.println("Deleted object is");
            new BufferedReader(new InputStreamReader(connection.getInputStream())).lines()
                    .forEach(System.out::println);
        }
        else
            System.out.println("Response is not ok: " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
        connection.disconnect();
        System.out.println("---End of removeUserByID method using HttpURLConnection\n\n");
    }
    public static void printUsersData() throws IOException, IllegalAccessException {
        System.out.println("printUsersData method using HttpURLConnection");
        connection = (HttpURLConnection) new URL(SOURCE_USERS).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            System.out.println("Response is " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
            Type userListType = new TypeToken<List<User>>(){}.getType();
            List<User> userList = new Gson().fromJson(new InputStreamReader(connection.getInputStream()), userListType);
            userList.forEach(System.out::println);
        }
        else
            System.out.println("Response is not ok: " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
        connection.disconnect();
        System.out.println("---End of printUsersData method using HttpURLConnection\n\n");
    }
    public static void printUserDataByID(int userID) throws IOException, IllegalAccessException {
        System.out.println("printUserDataByID method using HttpURLConnection");
        connection = (HttpURLConnection) new URL(SOURCE_USERS + "/" + userID).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            System.out.println("Response is " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
            System.out.println(new Gson().fromJson(new InputStreamReader(connection.getInputStream()), User.class));
        }
        else
            System.out.println("Response is not ok: " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
        connection.disconnect();
        System.out.println("---End of printUserDataByID method using HttpURLConnection\n\n");
    }
    public static void printUsersDataByName(String name) throws IOException, IllegalAccessException {
        System.out.println("printUsersDataByName method using HttpURLConnection");
        connection = (HttpURLConnection) new URL(SOURCE_USERS).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            System.out.println("Response is " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
            Type userListType = new TypeToken<List<User>>(){}.getType();
            List<User> userList = new Gson().fromJson(new InputStreamReader(connection.getInputStream()), userListType);
            long count = userList.stream()
                    .filter(e -> e.getName().equals(name))
                    .peek(System.out::println)
                    .count();
            if(count == 0)
                System.out.println("No user found with name " + name);
        }
        else
            System.out.println("Response is not ok: " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
        connection.disconnect();
        System.out.println("---End of printUsersDataByName method using HttpURLConnection\n\n");
    }
    private static int getLastPostIDByUserID(int userID) throws IllegalAccessException, IOException {
        connection = (HttpURLConnection) new URL(SOURCE_USERS + "/" + userID + "/posts").openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        Integer lastPostID = 0;
        if(responseCode == HttpURLConnection.HTTP_OK){
            System.out.println("Response is " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
            Type postListType = new TypeToken<List<Post>>(){}.getType();
            List<Post> postList = new Gson().fromJson(new InputStreamReader(connection.getInputStream()), postListType);
            lastPostID = postList.stream()
                    .sorted((e1, e2) -> Integer.compare(e2.getId(), e1.getId()))
                    .limit(1)
                    .map(Post::getId)
                    .collect(singleElementExtractor());
        }
        else
            System.out.println("Response while trying to get posts is not ok: " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
        connection.disconnect();
        if(lastPostID == null){
            lastPostID = 0;
        }
        return lastPostID;
    }
    public static void showAllCommentsFromLastPostOfDistinctUserByUserId(int userID) throws IllegalAccessException, IOException {
        System.out.println("showAllCommentsFromLastPostOfDistinctUserByUserId method using HttpURLConnection");
        int lastPostID = getLastPostIDByUserID(userID);
        if(lastPostID != 0) {
            HttpURLConnection connection = (HttpURLConnection) new URL(SOURCE_POSTS + "/" + lastPostID + "/comments").openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                System.out.println("Response is " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));

                FileOutputStream fi = new FileOutputStream("user-" + userID + "-" + "post-" + lastPostID + "-comments.json");
                connection.getInputStream().transferTo(fi);
                fi.close();
                InputStream fo = new FileInputStream("user-" + userID + "-" + "post-" + lastPostID + "-comments.json");
                Type commentsListType = new TypeToken<List<Comment>>(){}.getType();
                List<Comment> commentsList = new Gson().fromJson(new InputStreamReader(fo), commentsListType);
                commentsList.forEach(System.out::println);
            }
            else
                System.out.println("Response while trying to get comments is not ok: " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
        }
        else
            System.out.println("No any posts of this user");
        connection.disconnect();
        System.out.println("---End of showAllCommentsFromLastPostOfDistinctUserByUserId method using HttpURLConnection\n\n");
    }
    public static void showUnDoneToDoesForDistinctUser(int userID) throws IllegalAccessException, IOException {
        System.out.println("showUnDoneToDoesForDistinctUser method using HttpURLConnection");
        HttpURLConnection connection = (HttpURLConnection) new URL(SOURCE_USERS + "/" + userID + "/todos").openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            List<Todo> todoList;
            System.out.println("Response is " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));
            String body = new BufferedReader(new InputStreamReader(connection.getInputStream())).lines()
                    .collect(Collectors.joining());
            if(body.equals("{}")){
                System.out.println("No any ToDoes for user ID " + userID);
                return;
            }
            if(body.toCharArray()[0] == '{'){
                todoList = new LinkedList<Todo>();
                todoList.add(new Gson().fromJson(body, Todo.class));
            }
            else
            {
                Type toDoListType = new TypeToken<List<Todo>>(){}.getType();
                todoList = new Gson().fromJson(body, toDoListType);
            }
            todoList.stream()
                    .filter(e->!e.isCompleted())
                    .forEach(System.out::println);
        }
        else
            System.out.println("Response while trying to get comments is not ok: " + getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(responseCode));

        System.out.println("---End of showUnDoneToDoesForDistinctUser method using HttpURLConnection\n\n");
    }
    /*
    Якби HTTPURLConnection мав енам зі всіма своїми кодами відповідей то не потрібно було б знайомитись з рефлекшн апі
     */
    public static String getHTTPURLConnectionVerbalAndCodeResponseByResponseCode(int responseCode) throws IllegalAccessException {
        List<String> responses = new LinkedList<String>();
        int fieldModifiers;
        responses.add("test response");
        for(Field f:HttpURLConnection.class.getDeclaredFields()){
            fieldModifiers = f.getModifiers();
            if(Modifier.isFinal(fieldModifiers) && Modifier.isPublic(fieldModifiers) && Modifier.isStatic(fieldModifiers)){
                responses.add(f.get(f) + "\t"+ f.getName());
                //System.out.println((f.get(f) + "\t"+ f.getName())); //list all static public final fields of HTTPURLConnection.class
            }
        }
        return responses.stream()
                .filter(e->e.contains(String.valueOf(responseCode)))
                .limit(1)
                .collect(singleElementExtractor());
    }
    /**
     *
     * @return single element if it presents. null if no elements remains.
     * @throws IllegalStateException if quantity of elements to return is > 1.
     */
    public static <T> Collector<T, ?, T> singleElementExtractor() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                l -> {
                    if (l.size() > 1) {
                        throw new IllegalStateException();
                    }
                    if(l.size() == 0)
                        return null;
                    return l.get(0);
                }
        );
    }
}

