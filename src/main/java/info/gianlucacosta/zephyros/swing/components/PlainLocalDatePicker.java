/*^
  ===========================================================================
  Zephyros - Core
  ===========================================================================
  Copyright (C) 2017 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.zephyros.swing.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.time.LocalDate;

/**
 * Plain editor for LocalDate, especially focused on years: if
 * the month or day value is missing or non-numeric, it will be assumed
 * to be "1".
 */
public class PlainLocalDatePicker extends JPanel {
    private final JTextField yearField =
            new JTextField(6);

    private final JTextField monthField =
            new JTextField(3);

    private final JTextField dayField =
            new JTextField(3);


    public PlainLocalDatePicker() {
        setLayout(new FlowLayout());

        add(new JLabel("Year:"));
        add(yearField);

        add(new JLabel("Month:"));
        add(monthField);

        add(new JLabel("Day:"));
        add(dayField);
    }


    /**
     * @return The parsed LocalDate. The year must be valid (and can also be &lt; 0), but
     * the month and the day values will fall back to 1 in case of parsing errors.
     * If the overall LocalDate throws an Exception, it is not caught by this method.
     */
    public LocalDate getDate() {
        final int year;

        try {
            year =
                    Integer.parseInt(yearField.getText());
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Invalid year value");
        }


        int month;

        try {
            month = Integer.parseInt(monthField.getText());
        } catch (NumberFormatException ex) {
            month = 1;
        }


        int day;

        try {
            day = Integer.parseInt(dayField.getText());
        } catch (NumberFormatException ex) {
            day = 1;
        }

        return LocalDate.of(
                year,
                month,
                day
        );
    }


    /**
     * Sets the values in the controls according to the given LocalDate.
     * The month field is left empty if its value is "1"; the same for
     * the day field.
     *
     * @param date The LocalDate value
     */
    public void setDate(LocalDate date) {
        int year =
                date.getYear();


        yearField.setText(
                Integer.toString(year)
        );


        int month =
                date.getMonthValue();

        if (month > 1) {
            monthField.setText(
                    Integer.toString(month)
            );
        } else {
            monthField.setText("");
        }


        int day =
                date.getDayOfMonth();

        if (day > 1) {
            dayField.setText(
                    Integer.toString(day)
            );
        } else {
            dayField.setText("");
        }
    }
}
