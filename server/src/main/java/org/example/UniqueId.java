package org.example;

public class UniqueId {
    private static Integer id;

    public UniqueId() {
        id = 0;
    }

    public Request getNewId(Request request) {
        if (request.getCity()!=null) {
            request.getCity().setId(++id);
        }
            return request;

    }

    public static int getCurrentID() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
