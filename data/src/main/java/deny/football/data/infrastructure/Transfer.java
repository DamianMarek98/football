package deny.football.data.infrastructure;

public class Transfer {
    private final Long id;
    private Long clubFromId;
    private String clubFromName;
    private Long clubToId;
    private String clubToName;
    private String date;
    private Boolean upcoming;
    private String season;

    public Transfer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getClubFromId() {
        return clubFromId;
    }

    public void setClubFromId(Long clubFromId) {
        this.clubFromId = clubFromId;
    }

    public String getClubFromName() {
        return clubFromName;
    }

    public void setClubFromName(String clubFromName) {
        this.clubFromName = clubFromName;
    }

    public Long getClubToId() {
        return clubToId;
    }

    public void setClubToId(Long clubToId) {
        this.clubToId = clubToId;
    }

    public String getClubToName() {
        return clubToName;
    }

    public void setClubToName(String clubToName) {
        this.clubToName = clubToName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(Boolean upcoming) {
        this.upcoming = upcoming;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
