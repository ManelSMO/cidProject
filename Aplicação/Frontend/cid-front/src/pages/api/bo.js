export default async function handler(req, res) {
    const { cpf } = req.query;
  
    if (!cpf) {
      return res.status(400).json({ error: "CPF é obrigatório" });
    }
  
    // Simula um banco de dados
    const boFakeDB = [
      { id: 1, tipoOcorrencia: "Roubo", dataOcorrencia: "2024-11-19", localOcorrencia: "São Paulo", descricao: "Roubo a mão armada", cpf: "12345678900" },
      { id: 2, tipoOcorrencia: "Furto", dataOcorrencia: "2024-11-18", localOcorrencia: "Rio de Janeiro", descricao: "Furto de celular", cpf: "12345678900" },
    ];
  
    const resultados = boFakeDB.filter((bo) => bo.cpf === cpf);
  
    if (resultados.length === 0) {
      return res.status(404).json([]);
    }
  
    return res.status(200).json(resultados);
  }
  