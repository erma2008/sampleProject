package API.pojo;

import java.util.List;
import java.util.Map;

public class JiraBoardPojo {

    private int maxResults;
    private int startAt;
    private int total;
    private boolean isLast;

    public boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(boolean last) {
        isLast = last;
    }

    private List<Map<String,Object>> values;


    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



    public List<Map<String, Object>> getValues() {
        return values;
    }

    public void setValues(List<Map<String, Object>> values) {
        this.values = values;
    }
}
