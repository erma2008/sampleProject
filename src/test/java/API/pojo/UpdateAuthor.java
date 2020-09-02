package API.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class UpdateAuthor {

    private String self;
    private String name;
    private String key;
    private String emailAddress;
    private Map<String,String> avatarUrls;

    private String displayName;
    private String active;
    private String timeZone;

 }
