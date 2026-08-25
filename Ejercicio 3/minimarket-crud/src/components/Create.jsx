import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { collection, addDoc } from "firebase/firestore";
import { db } from "../services/firebase";
import { toast } from "sonner";

export default function Create() {
  const [description, setDescription] = useState("");
  const [stock, setStock] = useState("");
  const navigate = useNavigate();

  const coleccionRef = collection(db, "productos");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!description.trim() || !stock) {
      toast.warning("Completa todos los campos");
      return;
    }

    try {
      await addDoc(coleccionRef, {
        description: description.trim(),
        stock: Number(stock),
      });
      navigate("/");
    } catch (error) {
      toast.error("Error al guardar el producto");
    }
  };

  return (
    <div className="container py-4">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow-sm border-0">
            <div className="card-header bg-dark text-white">
              <h5 className="mb-0">
                <i className="bi bi-plus-circle me-2"></i>Crear Producto
              </h5>
            </div>
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label className="form-label fw-semibold">Descripción:</label>
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Ej: Alfajor Triple Chocolate"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                  />
                </div>
                <div className="mb-4">
                  <label className="form-label fw-semibold">Stock:</label>
                  <input
                    type="number"
                    className="form-control"
                    placeholder="0"
                    value={stock}
                    onChange={(e) => setStock(e.target.value)}
                  />
                </div>
                <div className="d-flex justify-content-end gap-2">
                  <Link to="/" className="btn btn-secondary">Cancelar</Link>
                  <button type="submit" className="btn btn-primary">
                    <i className="bi bi-save me-1"></i>Guardar
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}