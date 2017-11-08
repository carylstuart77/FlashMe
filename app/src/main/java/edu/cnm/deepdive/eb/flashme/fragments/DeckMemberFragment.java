package edu.cnm.deepdive.eb.flashme.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.j256.ormlite.dao.Dao;
import edu.cnm.deepdive.eb.flashme.DeckListActivity;
import edu.cnm.deepdive.eb.flashme.DeckMemberActivity;
import edu.cnm.deepdive.eb.flashme.R;
import edu.cnm.deepdive.eb.flashme.entities.Card;
import edu.cnm.deepdive.eb.flashme.entities.Deck;
import java.sql.SQLException;

/**
 * A fragment representing a single Student detail screen. This fragment is either contained in a
 * {@link DeckListActivity} in two-pane mode (on tablets) or a {@link DeckMemberActivity} on
 * handsets.
 */
public class DeckMemberFragment extends Fragment {

  /**
   * The fragment argument representing the item ID that this fragment represents.
   */
  public static final String ARG_ITEM_ID = "deck_id";

  /**
   * The student content this fragment is presenting.
   */
  private Deck mItem;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
   * screen orientation changes).
   */
  public DeckMemberFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments().containsKey(ARG_ITEM_ID)) {
      // Load the dummy content specified by the fragment
      // arguments. In a real-world scenario, use a Loader
      // to load content from a content provider.
      try {
        mItem = ((DeckMemberFragmentDaoInteraction) getContext())
            .getDaoDeck().queryForId(getArguments().getInt(ARG_ITEM_ID));
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

      Activity activity = this.getActivity();
      CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity
          .findViewById(R.id.toolbar_layout);
      if (appBarLayout != null) {
        appBarLayout.setTitle(mItem.getName());
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.deck_detail, container, false);

    // Show the dummy content as text in a TextView.
    if (mItem != null) {
      ((TextView) rootView.findViewById(R.id.deck_detail)).setText(mItem.toString());
      // TODO Set card entities
    }

    return rootView;
  }

  public interface DeckMemberFragmentDaoInteraction {
    Dao<Deck, Integer> getDaoDeck() throws SQLException;
  }

  public interface CardMemberFragmentDaoInteraction {
    Dao<Card, Integer> getDaoCard() throws SQLException;
  }
}
