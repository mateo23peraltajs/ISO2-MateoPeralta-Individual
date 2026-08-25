import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { collection, getDocs, deleteDoc, doc } from "firebase/firestore";
import { db } from "../services/firebase";
import { Toaster, toast } from "sonner";

export default function Show() {
  const [productos, setProductos] = useState([]);
  const [cargando, setCargando] = useState(false);

  const coleccionRef = collection(db, "productos");

  const obtenerProductos = async () => {
    setCargando(true);
    try {
      const data = await getDocs(coleccionRef);
      setProductos(data.docs.map((d) => ({ id: d.id, ...d.data() })));
    } catch (error) {
      toast.error("Error al cargar los productos");
    } finally {
      setCargando(false);
    }
  };

  useEffect(() => {
    obtenerProductos();
  }, []);

  const eliminarProducto = async (id, nombre) => {
    toast(`¿Eliminar ${nombre}?`, {
      action: {
        label: "Confirmar",
        onClick: async () => {
          try {
            await deleteDoc(doc(db, "productos", id));
            toast.success("Producto eliminado");
            obtenerProductos();
          } catch (error) {
            toast.error("No se pudo eliminar");
          }
        },
      },
      cancel: { label: "Cancelar" },
    });
  };

  return (
    <div className="container py-4">
      <Toaster position="top-right" richColors />
      
      <div className="d-flex justify-content-between align-items-center mb-4">
        <div>
          <h2 className="fw-bold">Inventario de Productos</h2>
          <p className="text-muted mb-0">Base de datos en Firebase Firestore</p>
        </div>
        <Link to="/create" className="btn btn-primary">
          <i className="bi bi-plus-lg me-2"></i>Crear Producto
        </Link>
      </div>

      <div className="card shadow-sm border-0">
        <div className="card-body p-0">
          <div className="table-responsive">
            <table className="table table-hover align-middle mb-0">
              <thead className="table-dark">
                <tr>
                  <th scope="col" className="ps-4">Descripción</th>
                  <th scope="col">Stock</th>
                  <th scope="col" className="text-end pe-4">Acciones</th>
                </tr>
              </thead>
              <tbody>
                {cargando ? (
                  <tr>
                    <td colSpan="3" className="text-center py-4 text-muted">Cargando datos...</td>
                  </tr>
                ) : productos.length === 0 ? (
                  <tr>
                    <td colSpan="3" className="text-center py-4 text-muted">No hay registros disponibles.</td>
                  </tr>
                ) : (
                  productos.map((prod) => (
                    <tr key={prod.id}>
                      <td className="ps-4 fw-semibold">{prod.description}</td>
                      <td>
                        <span className={`badge ${prod.stock <= 5 ? "bg-danger" : "bg-success"}`}>
                          {prod.stock} unidades
                        </span>
                      </td>
                      <td className="text-end pe-4">
                        <Link to={`/edit/${prod.id}`} className="btn btn-sm btn-outline-warning me-2">
                          <i className="bi bi-pencil"></i>
                        </Link>
                        <button
                          className="btn btn-sm btn-outline-danger"
                          onClick={() => eliminarProducto(prod.id, prod.description)}
                        >
                          <i className="bi bi-trash"></i>
                        </button>
                      </td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}