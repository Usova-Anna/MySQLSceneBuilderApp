

package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField new_task_field;

    @FXML
    private Button add_task;

    @FXML
    private VBox all_tasks;

    DB db = null;
    @FXML
    void initialize() {
        assert new_task_field != null : "fx:id=\"new_task_field\" was not injected: check your FXML file 'sample.fxml'.";
        assert add_task != null : "fx:id=\"add_task\" was not injected: check your FXML file 'sample.fxml'.";
        assert all_tasks != null : "fx:id=\"all_tasks\" was not injected: check your FXML file 'sample.fxml'.";
        db = new DB();
        add_task.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (!new_task_field.getText().trim().equals("")) {
                        db.insertTask(new_task_field.getText());
                        loadInfo();
                        new_task_field.setText("");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        loadInfo();
    }
void loadInfo(){
try{
    //очищаем VBOX от прошлых значений
    all_tasks.getChildren().clear();
    //Получаем все задания из базы
    ArrayList<String> tasks = db.getTasks();
    //Перебираем их через цикл до task.size()
    for (int i=0;i<tasks.size();i++) {
        //Добавляем каждое задание в объект VBox(all_tasks)
        all_tasks.getChildren().add(new Label(tasks.get(i)));
    }

} catch (SQLException e) {
    e.printStackTrace();
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
        }
    }

