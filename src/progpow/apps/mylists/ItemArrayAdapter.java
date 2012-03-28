package progpow.apps.mylists;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class ItemArrayAdapter extends ArrayAdapter<Item> {

	private final List<Item> list;
	private final Activity context;
	// used to keep selected position in ListView
			
	public ItemArrayAdapter(Activity context, List<Item> list) {
		super(context, R.layout.rowitemlayout, list);
		this.context = context;
		this.list = list;
	}
	
	static class ViewHolder {
		protected TextView text;
		protected CheckBox checkbox;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	View view = null;
		
		if (convertView == null) {
			final ViewHolder viewHolder = new ViewHolder();
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.rowitemlayout, null);			
			viewHolder.text = (TextView) view.findViewById(R.id.txtItemNameViewId);
			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.chkItemViewId);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							Item element = (Item) viewHolder.checkbox
									.getTag();
							element.setSelected(buttonView.isChecked());
						}
					});
			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(list.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
		}		
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(list.get(position).getName());		
		holder.checkbox.setChecked(list.get(position).isSelected());
		return view;
	}
}
