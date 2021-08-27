import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category {

    jsonUsers get_users = new jsonUsers();
    List<objUsers> users = get_users.get();
    Text text = new Text();
    int target;
    int counter=1,sounter = 1;
    int respons;
    private static final Logger logger = LogManager.getLogger(Category.class);

    int[] mapper = new int [10000];
    int[] chater = new int [10000];
    int use;
    int cat;
    String res;
    List<String> newcat ;
    public Category(String username) throws IOException {
        logger.info("System: user went to Category");
        for (int i = 0; i < users.size() ; i++)
            if (users.get(i).getUsername().equals(username)){
                target = i;
                break;
            }

        text.printer("\nCategory:",ConsoleColors.CYAN_BOLD);
        text.printer("a-Creat a new category",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("b-Add a following to a category",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("c-Send message to a category",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("d-Remove a user from a category",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("e-See persons of categories",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("m-Send message to a category",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("f-Remove a category",ConsoleColors.BLACK_BOLD_BRIGHT);
        text.printer("g-go to setting\n",ConsoleColors.BLACK_BOLD_BRIGHT);


        switch (text.getText()){
            case "a":
                text.printer("\nInsert name of the category\n",ConsoleColors.BLACK_BOLD_BRIGHT);
                users.get(target).getCategoiries().add(Collections.singletonList(text.getText()));
                new jsonUsers (users);
                logger.info("System: Category created!");
                text.printer("\nCategory created!\n",ConsoleColors.GREEN_BOLD);
                text.getText();
                new Category(username);
                return;

            case "b":
                use = getFollowings(username);
                cat = getCategories(username);
                if (cat == -1||use == -1) {
                    new Category(username);
                    return;
                }
                if (!users.get(target).getCategoiries().get(chater[cat-1]).
                        contains( users.get(target).getFollowings().get(mapper[use-1]))) {
                    users.get(target).getCategoiries().get(chater[cat - 1]).add(
                            users.get(target).getFollowings().get(mapper[use - 1]));
                    new jsonUsers(users);
                    logger.info("System: User added to selected category!");
                    text.printer("\nUser added to selected category!", ConsoleColors.GREEN_BOLD);
                    text.getText();
                }else{
                    text.printer("\nUser already exist in this category! ", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                }
                new Category(username);
                return;

            case "d":

                cat = getCategories(username);
                if (cat == -1) {
                    return;
                }


                new nextPage(1);
                counter=1;
                if (users.get(target).getFollowings().size() != 1){
                    text.printer( users.get(target).getCategoiries().get(chater[cat-1]).get(0)+" Category:", ConsoleColors.CYAN_BOLD);
                    for (int j = 1; j < users.get(target).getCategoiries().get(chater[cat-1]).size(); j++){
                                text.printer(counter + "- " +
                                                users.get(target).getCategoiries().get(chater[cat-1]).get(j),
                                        ConsoleColors.BLACK_BOLD_BRIGHT);
                                mapper[counter - 1] = j;
                                counter++;
                            }
                }

                if (counter == 1) {
                    text.printer("Your category is empty!\n", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    new Category(username);
                    return;
                } else {
                    text.printer("\nInsert users number to remove:", ConsoleColors.PURPLE_BOLD);
                    try {
                        int resnum2 = Integer.parseInt(text.getText());
                        if (resnum2 < counter) {


                            users.get(target).getCategoiries().get(chater[cat-1]).remove(
                                    users.get(target).getCategoiries().get(chater[cat-1]).
                                    get(mapper[resnum2-1]));

                            new jsonUsers(users);
                            logger.info("System: User removed selected category!");
                            text.printer("\nUser removed selected category!", ConsoleColors.RED_BOLD_BRIGHT);
                            text.getText();
                            new Category(username);
                            return;


                        } else {
                            text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                            text.getText();
                            new Category(username);
                            return;
                        }
                    } catch (NumberFormatException | IOException exception) {
                        new Category(username);
                        return;
                    }
                }

            case "e":

                cat = getCategories(username);
                if (cat == -1) {
                    return;
                }

                new nextPage(1);
                counter=1;
                if (users.get(target).getFollowings().size() != 1){
                    text.printer( users.get(target).getCategoiries().
                            get(chater[cat-1]).get(0)+" Category:", ConsoleColors.CYAN_BOLD);
                    for (int j = 1; j < users.get(target).getCategoiries().get(chater[cat-1]).size(); j++){
                        text.printer(counter + "- " +
                                        users.get(target).getCategoiries().get(chater[cat-1]).get(j),
                                ConsoleColors.BLACK_BOLD_BRIGHT);
                        mapper[counter - 1] = j;
                        counter++;
                    }
                }

                if (counter == 1) {
                    text.printer("Your category is empty!", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    new Category(username);
                    return;
                }
                new Category(username);
                return;
            case "f":
                cat = getCategories(username);
                if (cat == -1) {
                    return;
                }
                text.printer("\nAre you sure?(y/n)", ConsoleColors.BLUE_BOLD);
                switch (text.getText()){
                    case "y":   users.get(target).getCategoiries().remove(chater[cat-1]);
                        new jsonUsers(users);
                        logger.info("System: Category removed");
                    text.printer("\nCategory removed\n", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                        break;
                    case "n": break;

                }

            case "m":
                new massMessenger(username);
                return;

            case "g":
                new Setting(username);
                return;

            default: text.printer("\nInvalid command!", ConsoleColors.RED_BOLD_BRIGHT);
                new Category(username);
                return;
        }



    }
    public int getFollowings(String username) throws IOException {
        counter=1;
        if (users.get(target).getFollowings().size() != 1){
            text.printer("\nYour Followings:", ConsoleColors.CYAN_BOLD);
            for (int j = 1; j < users.get(target).getFollowings().size(); j++)
                for (int i = 0; i < users.size(); i++)
                    if (users.get(i).getUsername().equals(users.get(target).getFollowings().get(j))
                            && users.get(i).getIsEnable()){
                        text.printer(counter + "- " + users.get(target).getFollowings().get(j),
                                ConsoleColors.BLACK_BOLD_BRIGHT);
                        mapper[counter - 1] = j;
                        counter++;
                    }
        }
        if (counter == 1) {
            text.printer("You dont follow anybody!", ConsoleColors.RED_BOLD_BRIGHT);
        } else {
            text.printer("\nInsert users number:", ConsoleColors.PURPLE_BOLD);
            try {
                int resnum2 = Integer.parseInt(text.getText());
                if (resnum2 < counter) {
                    return resnum2;
                } else {
                    text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    new Category(username);
                    return -1 ;
                }
            } catch (NumberFormatException | IOException exception) {
                new Category(username);
                return -1 ;
            }
        }
        return -1;
    }
    public int getCategories(String username) throws IOException {
        if (users.get(target).getCategoiries().size() != 1) {
            text.printer("\nYour Categories:", ConsoleColors.CYAN_BOLD);
            for (int j = 1; j < users.get(target).getCategoiries().size(); j++) {
                text.printer(sounter + "- " + users.get(target).getCategoiries().get(j).get(0),
                        ConsoleColors.BLACK_BOLD_BRIGHT);
                chater[sounter - 1] = j;
                sounter++;
            }
        }

        if (sounter == 1) {
            text.printer("You dont have any category!", ConsoleColors.RED_BOLD_BRIGHT);
        } else {
            text.printer("\nInsert category number:", ConsoleColors.PURPLE_BOLD);
            try {
                int resnum = Integer.parseInt(text.getText());
                if (resnum < sounter) {
                    return resnum;
                } else {
                    text.printer("\nPlease insert valid number!", ConsoleColors.RED_BOLD_BRIGHT);
                    text.getText();
                    new Category(username);
                    return -1;
                }
            } catch (NumberFormatException | IOException exception) {
                new Category(username);
                return -1;
            }
        }
return -1;
    }
}
