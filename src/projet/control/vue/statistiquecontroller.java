package projet.control.vue;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class statistiquecontroller {
	
	@FXML
	private BarChart<String, Integer> barchat;
	
	@FXML
	private CategoryAxis xAxis;
	
	private ObservableList<String> mois=FXCollections.observableArrayList();
	

	@FXML
	private void initialize()
	{
	}
	
	@FXML
	private void Stateaffiche()
	{
		int[] nombremois=new int[12];
		for(int u=0;u<12;u++)
		{
			nombremois[u]=u+2;
		}
		
		XYChart.Series<String, Integer> series =new XYChart.Series<>();
		
		for(int i=0;i<12;i++)
		{
			series.getData().add(new XYChart.Data<>(mois.get(i),nombremois[i]));
		}
		
		barchat.getData().add(series);
	}
}
