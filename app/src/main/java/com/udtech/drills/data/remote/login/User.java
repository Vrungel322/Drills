package com.udtech.drills.data.remote.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

  @SerializedName("id") @Expose private Integer id;
  @SerializedName("username") @Expose private String username;
  @SerializedName("auth_key") @Expose private String authKey;
  @SerializedName("name") @Expose private String name;
  @SerializedName("lastname") @Expose private String lastname;
  @SerializedName("profile_photo") @Expose private String profilePhoto;
  @SerializedName("type_id") @Expose private Integer typeId;
  @SerializedName("type") @Expose private String type;
  @SerializedName("team") @Expose private String team;
  @SerializedName("player") @Expose private Object player;
  @SerializedName("email") @Expose private String email;
  @SerializedName("phone") @Expose private String phone;
  @SerializedName("country") @Expose private Country country;
  @SerializedName("region") @Expose private Region region;
  @SerializedName("city") @Expose private City city;
  public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {

    @SuppressWarnings({
        "unchecked"
    }) public User createFromParcel(Parcel in) {
      User instance = new User();
      instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
      instance.username = ((String) in.readValue((String.class.getClassLoader())));
      instance.authKey = ((String) in.readValue((String.class.getClassLoader())));
      instance.name = ((String) in.readValue((String.class.getClassLoader())));
      instance.lastname = ((String) in.readValue((String.class.getClassLoader())));
      instance.profilePhoto = ((String) in.readValue((String.class.getClassLoader())));
      instance.typeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
      instance.type = ((String) in.readValue((String.class.getClassLoader())));
      instance.team = ((String) in.readValue((String.class.getClassLoader())));
      instance.player = ((Object) in.readValue((Object.class.getClassLoader())));
      instance.email = ((String) in.readValue((String.class.getClassLoader())));
      instance.phone = ((String) in.readValue((String.class.getClassLoader())));
      instance.country = ((Country) in.readValue((Country.class.getClassLoader())));
      instance.region = ((Region) in.readValue((Region.class.getClassLoader())));
      instance.city = ((City) in.readValue((City.class.getClassLoader())));
      return instance;
    }

    public User[] newArray(int size) {
      return (new User[size]);
    }
  };

  /**
   * No args constructor for use in serialization
   */
  public User() {
  }

  /**
   *
   * @param region
   * @param phone
   * @param authKey
   * @param player
   * @param lastname
   * @param team
   * @param type
   * @param city
   * @param country
   * @param id
   * @param username
   * @param email
   * @param name
   * @param profilePhoto
   * @param typeId
   */
  public User(Integer id, String username, String authKey, String name, String lastname,
      String profilePhoto, Integer typeId, String type, String team, Object player, String email,
      String phone, Country country, Region region, City city) {
    super();
    this.id = id;
    this.username = username;
    this.authKey = authKey;
    this.name = name;
    this.lastname = lastname;
    this.profilePhoto = profilePhoto;
    this.typeId = typeId;
    this.type = type;
    this.team = team;
    this.player = player;
    this.email = email;
    this.phone = phone;
    this.country = country;
    this.region = region;
    this.city = city;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAuthKey() {
    return authKey;
  }

  public void setAuthKey(String authKey) {
    this.authKey = authKey;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getProfilePhoto() {
    return profilePhoto;
  }

  public void setProfilePhoto(String profilePhoto) {
    this.profilePhoto = profilePhoto;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  public Object getPlayer() {
    return player;
  }

  public void setPlayer(Object player) {
    this.player = player;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(id);
    dest.writeValue(username);
    dest.writeValue(authKey);
    dest.writeValue(name);
    dest.writeValue(lastname);
    dest.writeValue(profilePhoto);
    dest.writeValue(typeId);
    dest.writeValue(type);
    dest.writeValue(team);
    dest.writeValue(player);
    dest.writeValue(email);
    dest.writeValue(phone);
    dest.writeValue(country);
    dest.writeValue(region);
    dest.writeValue(city);
  }

  public int describeContents() {
    return 0;
  }

  @Override public String toString() {
    return "id : "
        + this.getId()
        + "\n"
        + "userName : "
        + this.getUsername()
        + "\n"
        + "AuthKey : "
        + this.getAuthKey()
        + "\n"
        + "Name : "
        + this.getName()
        + "\n";
  }
}
