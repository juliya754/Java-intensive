package model;

public class TODO {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TODO todo = (TODO) o;

        if (id != todo.id) return false;
        if (isDone != todo.isDone) return false;
        return comment != null ? comment.equals(todo.comment) : todo.comment == null;

    }


    @Override
    public String toString() {
        return "TODO{" +
                "id=" + id +
                ", isDone=" + isDone +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isDone ? 1 : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    private long id;
    private boolean isDone;
    private String comment;

    public TODO(long id, boolean isDone, String comment) {
        this.id = id;
        this.isDone = isDone;
        this.comment = comment;
    }



    public void setId(long id) {
        this.id = id;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getComment() {
        return comment;
    }


}
