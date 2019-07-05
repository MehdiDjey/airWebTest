package fr.airweb.news.viewmodel;

import androidx.lifecycle.ViewModel;

import fr.airweb.news.model.Item;

import fr.airweb.news.utils.service.AirWebService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@SuppressWarnings("WeakerAccess")
public class ItemViewModel extends ViewModel {

    public Observable<Item> loadItem() {

      return AirWebService.getInstance().provideClient()
              .getItems()
              .subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread());

    }

}
