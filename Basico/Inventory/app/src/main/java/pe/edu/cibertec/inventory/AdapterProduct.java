package pe.edu.cibertec.inventory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

// Importante heredar de la clase RecyclerView.Adapter<VH>
public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ProductPrototype> {

    ArrayList<Product> items;

    public AdapterProduct(ArrayList<Product> items) {
        this.items = items;
    }

    // Crea la vista en base al prototipo por cada celda
    @NonNull
    @Override
    public ProductPrototype onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prototype_product,
                viewGroup, false);

        ProductPrototype productPrototype = new ProductPrototype(view);

        return productPrototype;
    }

    // Carga la informción por cada celda de acuerdo a la posición
    @Override
    public void onBindViewHolder(@NonNull ProductPrototype productPrototype, int position) {

        Product product = items.get(position);

        productPrototype.tvName.setText(product.getName());
        productPrototype.tvDescription.setText(product.getDescription());

        productPrototype.tvQuantity.setText(String.valueOf(product.getQuantity()));
    }

    // Indica la cantidad de registros en el RecyclerView
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ProductPrototype extends RecyclerView.ViewHolder {

        TextView tvName, tvDescription, tvQuantity;

        public ProductPrototype(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);

        }
    }
}
