package progpow.apps.mylists;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class InteractiveArrayAdapter extends ArrayAdapter<Model> {

	
	private final List<Model> list;
	private final Activity context;
	// used to keep selected position in ListView
		
	
	public InteractiveArrayAdapter(Activity context, List<Model> list) {
		super(context, R.layout.rowlistlayout, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView text;
		protected TextView desc;	
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		
		if (convertView == null) {
			final ViewHolder viewHolder = new ViewHolder();
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.rowlistlayout, null);			
			viewHolder.text = (TextView) view.findViewById(R.id.lblListNameId);
			viewHolder.desc=(TextView) view.findViewById(R.id.lblListDescId);
			//viewHolder.checkbox = (CheckBox) view.findViewById(R.id.lblListChk);
			//viewHolder.checkbox
			//		.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					//	@Override
					//	public void onCheckedChanged(CompoundButton buttonView,
					//			boolean isChecked) {
					//		Model element = (Model) viewHolder.checkbox
					//				.getTag();
					//		element.setSelected(buttonView.isChecked());

					//	}
					//});
			view.setTag(viewHolder);
			///viewHolder.checkbox.setTag(list.get(position));
		} else {
			view = convertView;
		///	((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
		}		
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(list.get(position).getName());
		holder.desc.setText(list.get(position).getDescription());
	///	holder.checkbox.setChecked(list.get(position).isSelected());
		return view;
	}
}

