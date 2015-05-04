package todoapp.gui;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class LabeledTextField extends TextField
{
	private boolean placeholder = true;

	private StringProperty valueProperty = new SimpleStringProperty();

	public LabeledTextField(String label)
	{
		setText(label);
		getStyleClass().add("textField-empty");

		focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (newValue)
				{
					if (placeholder)
					{
						placeholder = false;
						setText("");
						getStyleClass().removeAll("textField-empty");
					}
				}
				else
				{
					if (getText().length() == 0)
					{
						placeholder = true;
						setText(label);
						getStyleClass().add("textField-empty");
					}
				}
			}
		});

		textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				valueProperty.setValue(placeholder ? "" : newValue);
			}
		});
	}

	public ReadOnlyStringProperty valueProperty()
	{
		return valueProperty;
	}
}
