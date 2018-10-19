package za.co.amjsolutions.swingy.db;

<<<<<<< HEAD
import za.co.amjsolutions.swingy.models.Hero;
import za.co.amjsolutions.swingy.models.HeroBuilder;
import za.co.amjsolutions.swingy.models.artefacts.Armor;
import za.co.amjsolutions.swingy.models.artefacts.Helm;
import za.co.amjsolutions.swingy.models.artefacts.Weapon;
=======
import za.co.amjsolutions.swingy.exceptions.HeroNotFoundException;
import za.co.amjsolutions.swingy.models.Hero;
import za.co.amjsolutions.swingy.models.HeroBuilder;
import za.co.amjsolutions.swingy.models.HeroFactory;
>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
    private static final String connectionUrl = "jdbc:sqlite:/goinfre/jmsimang/Desktop/2nd-year-projects/Java/Swingy/src/main/java/za/co/amjsolutions/swingy/db/swingy"; //MacOS
//    private static final String connectionUrl = "jdbc:sqlite:/home/jmsimang/Desktop/2nd-year-projects/Java/Swingy/src/main/java/za/co/amjsolutions/swingy/db/swingy"; //Linux
    private static Connection connection;
<<<<<<< HEAD
    private ResultSet rs;
=======
    //home/jmsimang/Desktop/2nd-year-projects
>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd

    public static void connectToDB() {
        Connection conn = null;
        try {
<<<<<<< HEAD
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionUrl);
//            if (conn != null) {
//                DatabaseMetaData meta = conn.getMetaData();
//                System.out.println("SQL Lite driver name: " + meta.getDriverName());
//                System.out.println("DB connection established successfully");
//            }
=======
            System.out.println("Attempting to connect to SQLite Server ... ");
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectionUrl);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
//                System.out.println("SQL Lite driver name: " + meta.getDriverName());
//                System.out.println("DB connection established successfully");
            }
>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        connection = conn;
    }

    private static Connection getConnection() {
        if (connection == null) { connectToDB(); }
        return connection;
    }

    public static ArrayList<String> selectAllHeroes() {
        String query = "SELECT * FROM heroes";
<<<<<<< HEAD
        ArrayList<String> heroList = new ArrayList<>();
=======
        ArrayList<String> heroList = new ArrayList<String>();

>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
<<<<<<< HEAD
                heroList.add(String.format("%d\t%s\t%s",
                    rs.getInt("hero_id"),
                        rs.getString("hero_name"),
                            rs.getString("hero_class")));
=======
                heroList.add(String.format("%d. Name: %s, \tClass: %s, \tAttack: %d, \tDefense: %d, \tHP: %d, \tLevel: %d, \tXP: %d",
                        rs.getInt("hero_id"),rs.getString("hero_name"), rs.getString("hero_class"), rs.getInt("attack"),
                        rs.getInt("defense"), rs.getInt("hit_points"), rs.getInt("level"), rs.getInt("xp_points")));

>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return heroList;
    }

<<<<<<< HEAD
    public static Hero selectHeroById(int hero_id) {
        Hero hero = null;
=======
    public static ArrayList<Hero> retrieveAllHeroes() {
        String query = "SELECT * FROM heroes";
        ArrayList<Hero> heroes = new ArrayList<Hero>();

        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                heroes.add(HeroFactory.newHero(rs.getString("hero_name"), rs.getString("hero_class")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (HeroNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return heroes;
    }

    public static Hero selectHeroById(int hero_id) {
        Hero hero = null;
        HeroBuilder heroBuilder;
>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
        String query = "SELECT * FROM heroes WHERE hero_id = ?";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, hero_id);
            ResultSet rs = stmt.executeQuery();
<<<<<<< HEAD
            if (rs.next()) {
                HeroBuilder heroBuilder = new HeroBuilder();
                heroBuilder.setHeroID(rs.getInt("hero_id"))
                .setName(rs.getString("hero_name"))
=======
            while (rs.next()) {
                heroBuilder = new HeroBuilder();
                heroBuilder.setName(rs.getString("hero_name"))
>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
                .setHeroClass(rs.getString("hero_class"))
                .setAttack(rs.getInt("attack"))
                .setDefense(rs.getInt("defense"))
                .setHitPoints(rs.getInt("hit_points"))
                .setLevel(rs.getInt("level"))
                .setXP(rs.getInt("xp_points"));
<<<<<<< HEAD
            if (rs.getString("armor_type") != null)
                heroBuilder.setArmor(new Armor(rs.getString("armor_type"), rs.getInt("armor_points")));
            if (rs.getString("helm_type") != null)
                heroBuilder.setHelm(new Helm(rs.getString("helm_type"), rs.getInt("helm_points")));
            if (rs.getString("weapon_type") != null)
                heroBuilder.setWeapon(new Weapon(rs.getString("weapon_type"), rs.getInt("weapon_points")));
                hero = heroBuilder.getHero();
=======
            hero = heroBuilder.getHero();
>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return hero;
    }

<<<<<<< HEAD
    public static int createAHero(String hero_name, String hero_class, int attack, int defense, int hit_points, int level, int xp_points) {
        int id = 0;
        String query = "INSERT INTO heroes (hero_name, hero_class, attack, defense, hit_points, level, xp_points) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, hero_name);
            stmt.setString(2, hero_class);
            stmt.setInt(3, attack);
            stmt.setInt(4, defense);
            stmt.setInt(5, hit_points);
            stmt.setInt(6, level);
            stmt.setInt(7, xp_points);
            stmt.executeUpdate();

            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT seq FROM sqlite_sequence WHERE name = \"heroes\"");
            if (rs.next())
                id = rs.getInt("seq");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return id;
    }

    public static void updateHero(Hero hero) {
        String query = "UPDATE heroes SET attack = ?, defense = ?, hit_points = ?, level = ?, xp_points = ?," +
            "armor_type = ?, armor_points = ?, helm_type = ?, helm_points = ?, weapon_type = ?, weapon_points = ? " +
                "WHERE hero_id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, hero.getAttack());
            stmt.setInt(2, hero.getDefense());
            stmt.setInt(3, hero.getHp());
            stmt.setInt(4, hero.getLevel());
            stmt.setInt(5, hero.getXp());
            if (hero.getArmor() != null) {
                stmt.setString(6, hero.getArmor().getType());
                stmt.setInt(7, hero.getArmor().getPoints());
            } else {
                stmt.setString(6, null);
                stmt.setInt(7, 0);
            }
            if (hero.getHelm() != null) {
                stmt.setString(8, hero.getHelm().getType());
                stmt.setInt(9, hero.getHelm().getPoints());
            } else {
                stmt.setString(8, null);
                stmt.setInt(9, 0);
            }
            if (hero.getWeapon() != null) {
                stmt.setString(10, hero.getWeapon().getType());
                stmt.setInt(11, hero.getWeapon().getPoints());
            } else {
                stmt.setString(10, null);
                stmt.setInt(11, 0);
            }
            stmt.setInt(12, hero.getHeroID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("UPDATE ERROR: "+e.getMessage());
        }
    }

    public static void closeDBConnection() {
        try {
            if (connection != null)
                connection.close();
=======
    public static void closeDBConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection to SQLite has been closed!");
                connection = null;
            }
>>>>>>> 52f51ea9df11b563539e018eea940ebcaf2104cd
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
