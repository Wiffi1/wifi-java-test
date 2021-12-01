package students.model;

public class Language {
    private int id;
    private  String name;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Language[");
        builder.append("id=").append(id);
        builder.append(", name=").append(name);
        builder.append(", code=").append(code);
        builder.append(']');
        return builder.toString();
    }
}
