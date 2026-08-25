import { useState, useEffect } from "react";
import { useParams, useNavigate, Link } from "react-router-dom";
import { getDoc, updateDoc, doc } from "firebase/firestore";
import { db } from "../services/firebase";
import { toast } from "sonner";

export default function Edit() {
  const [description, setDescription] = useState("");
  const [stock, setStock] = useState("");
  const { id } = useParams();
  const navigate = useNavigate();

  // Obtener los datos del documento por su ID
  const obtenerProductoPorId = async (idDoc) => {
    const docRef = doc(db, "productos", idDoc);
    const docSnap = await getDoc(docRef);

    if (docSnap.exists()) {
      setDescription(docSnap.data().description);
      setStock(docSnap.data().stock);
    } else {
      toast.error("El producto no existe");
      navigate("/");
    }
  };

  useEffect(() => {
    obtenerProductoPorId(id);
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!description.trim() || !stock) {
      toast.warning("Completa todos los campos");
      return;
    }

    try {
      const docRef = doc(db, "productos", id);
      await updateDoc(docRef, {
        description: description.trim(),
        stock: Number(stock),
      });
      navigate("/");
    } catch (error) {
      toast.error("Error al actualizar el producto");
    }
  };

  return (
    <div className="container py-4">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow-sm border-0">
            <div className="card-header bg-dark text-white">
              <h5 className="mb-0">
                <i className="bi bi-pencil-square me-2"></i>Editar Producto
              </h5>
            </div>
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label className="form-label fw-semibold">Descripción:</label>
                  <input
                    type="text"
                    className="form-control"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                  />
                </div>
                <div className="mb-4">
                  <label className="form-label fw-semibold">Stock:</label>
                  <input
                    type="number"
                    className="form-control"
                    value={stock}
                    onChange={(e) => setStock(e.target.value)}
                  />
                </div>
                <div className="d-flex justify-content-end gap-2">
                  <Link to="/" className="btn btn-secondary">Cancelar</Link>
                  <button type="submit" className="btn btn-warning">
                    <i className="bi bi-check-lg me-1"></i>Actualizar
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