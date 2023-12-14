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
    private String fee;

    public Transfer(Long id) {
        this.id = id;
    }

    public static Transfer from(deny.football.data.transfermarkt.dto.Transfer transferDto) {
        Transfer transfer = new Transfer(transferDto.id());
        transfer.setClubFromId(transferDto.from().clubID());
        transfer.setClubFromName(transferDto.from().clubName());
        transfer.setClubToId(transferDto.to().clubID());
        transfer.setClubToName(transferDto.to().clubName());
        transfer.setDate(transferDto.date());
        transfer.setUpcoming(transferDto.upcoming());
        transfer.setSeason(transferDto.season());
        transfer.setFee(transferDto.fee());
        return transfer;
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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
