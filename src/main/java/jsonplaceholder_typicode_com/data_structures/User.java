package jsonplaceholder_typicode_com.data_structures;

public class User {
    private int id;
    private String name;
    private String userName;
    private String eMail;
    private Address address;
    private String phone;
    private String webSite;
    private Company company;
    public User(int id, String name, String userName, String eMail, Address address, String phone, String webSite, Company company){
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.eMail = eMail;
        this.address = address;
        this.phone = phone;
        this.webSite = webSite;
        this.company = company;
    }
    public User() {
        this(1, "Petro", "Perto", "Perto@gmail.com", new Address(), "0-000-000-00-00", "Petro.pl", new Company());
    }

    @Override
    public String toString() {
        return "User{" +
                "\nid=" + id +
                "\nname='" + name + '\'' +
                "\nuserName='" + userName + '\'' +
                "\neMail='" + eMail + '\'' +
                "\naddress=" + address +
                "\nphone='" + phone + '\'' +
                "\nwebSite='" + webSite + '\'' +
                "\ncompany=" + company +
                "\n}";
    }
    public static void showDifference(User user, User anOtherUser){
        System.out.println("Difference between");
        System.out.printf("%-12s%-40s%-10s%-40s\n","field name", "user", "|", "an other user");
        if(user.id != anOtherUser.id)
            System.out.printf("%-12s%-40d%-10s%-40d\n","id", user.id, "|", anOtherUser.id);
        if(!user.name.equals(anOtherUser.name))
            System.out.printf("%-12s%-40s%-10s%-40s\n","name", user.name, "|", anOtherUser.name);;
        if(!user.userName.equals(anOtherUser.userName))
            System.out.printf("%-12s%-40s%-10s%-40s\n","userName", user.userName, "|", anOtherUser.userName);
        if(!user.eMail.equals(anOtherUser.eMail))
            System.out.printf("%-12s%-40s%-10s%-40s\n","eMail", user.eMail, "|", anOtherUser.eMail);
        if(!user.address.equals(anOtherUser.address))
            System.out.printf("%-12s%-40s%-10s%-40s\n","address", user.address, "|", anOtherUser.address);
        if(!user.phone.equals(anOtherUser.phone))
            System.out.printf("%-12s%-40s%-10s%-40s\n","phone", user.phone, "|", anOtherUser.phone);
        if(!user.webSite.equals(anOtherUser.webSite))
            System.out.printf("%-12s%-40s%-10s%-40s\n","webSite", user.webSite, "|", anOtherUser.webSite);
        if(!user.company.equals(anOtherUser.company))
            System.out.printf("%-12s%-40s%-10s%-40s\n","company", user.company, "|", anOtherUser.company);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

