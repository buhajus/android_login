package com.example.orai;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

//CustomWeatherList - adapteris, kuris sujungs duomenis iš DB su vaizdu (xml)
public class CustomWeatherList extends BaseAdapter {

    private Activity context;
    private ArrayList<Weather> weathers;
    private PopupWindow popup;
    private WeatherDAO weatherDAO;

    public CustomWeatherList(Activity context, ArrayList<Weather> weathers, WeatherDAO weatherDAO) {
        this.context = context;
        this.weathers = weathers;
        this.weatherDAO = weatherDAO;
    }

    public void setWeathers(ArrayList<Weather> weathers) {
        this.weathers = weathers;
    }



    //šios klasės viduje - aprašomi GUI elementai (visas įrašo vaizdas)
    public static class ViewHolder {
        TextView textViewId;
        TextView textViewCountry;
        TextView textViewDegrees;
        Button editButton;
        Button deleteButton;
    }

    @Override
    public int getCount() {
        return weathers.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //šio metodo viduje susiesime ViewHolder su GUI elementais ir užpildysime duomenimis iš DB
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            row = inflater.inflate(R.layout.row_item, null, true);

            //susiejame kodą su vaizdu
            holder.textViewId = row.findViewById(R.id.id_txt);
            holder.textViewCountry = row.findViewById(R.id.country_txt);
            holder.textViewDegrees = row.findViewById(R.id.degrees_txt);
            holder.editButton = row.findViewById(R.id.edit_btn);
            holder.deleteButton = row.findViewById(R.id.delete_btn);

            //išsaugo holder kartu su vaizdu
            row.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //vaizdo užpildymas įrašais iš DB
        //("" +) - konvertuoja į String
        holder.textViewId.setText("" + weathers.get(position).getId());
        holder.textViewCountry.setText(weathers.get(position).getCountryName());
        holder.textViewDegrees.setText("" + weathers.get(position).getDegrees());

        final int positionPopup = position;

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPopup(positionPopup);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDAO.delete(weathers.get(positionPopup));
                //kai ištriname įrašą - reikia atnaujinti sąrašą
                //turime nurtodyti(castinti) kokio listo norime
                weathers = (ArrayList<Weather>) weatherDAO.getAll();
                notifyDataSetChanged();
            }
        });

        return row;
    }

    private void editPopup(final int positionPopup) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.edit_popup,
                (ViewGroup) context.findViewById(R.id.popup));
        popup = new PopupWindow(layout, 600, 670, true);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        //susiejamas kodas su vaizdu
        final EditText country = layout.findViewById(R.id.edit_country);
        final EditText degrees = layout.findViewById(R.id.edit_degrees);
        Button save = layout.findViewById(R.id.save_btn);
        country.setText(weathers.get(positionPopup).getCountryName());
        degrees.setText("" + weathers.get(positionPopup).getDegrees());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getCountry = country.getText().toString();
                String getDegrees = degrees.getText().toString();
                Weather weather = weathers.get(positionPopup);
                weather.setCountryName(getCountry);
                weather.setDegrees(Double.parseDouble(getDegrees));

                weatherDAO.update(weather);
                //paredagavus - atnaujiname vaizdą
                weathers = (ArrayList<Weather>) weatherDAO.getAll();
                notifyDataSetChanged();
                popup.dismiss();
            }
        });

    }

}
