package infrastructure.dao;


import domain.models.Profissional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessionalDao {
    private String tableName = "profissionais";
    public static Connection connection = new ConnectionFactory().getConnection();
    public List<Profissional> getProfessional() {
        List<Profissional> profissionais = new ArrayList<Profissional>();

        try {
            PreparedStatement querySql = connection.prepareStatement("SELECT * FROM profissionais;");
            ResultSet queryResult = querySql.executeQuery();

            while (queryResult.next()) {
                Profissional profissional = new Profissional();
                profStatment(profissional, queryResult);
                profissionais.add(profissional);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        return profissionais;
    }

    public Profissional getProfessional(int id) {
        Profissional profissional;
        String sql = "SELECT * FROM " + this.tableName + " WHERE id=?;";
        try {
            PreparedStatement querySql = connection.prepareStatement(sql);
            querySql.setInt(1, id);
            ResultSet queryResult = querySql.executeQuery();
            queryResult.next();
            profissional = new Profissional(
                    queryResult.getInt("id"),
                    queryResult.getString("nome"),
                    queryResult.getString("profissao"),
                    queryResult.getDouble("salario")
            );
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return profissional;
    }

    public boolean addProfessional(String nome, String profissao, double salario) {
        String sql = String.format("INSERT INTO %s (nome, profissao, salario)" +
                " VALUES (?, ?, ?);", this.tableName);
        try {
            PreparedStatement querySql = connection.prepareStatement(sql);
            querySql.setString(1, nome);
            querySql.setString(2, profissao);
            querySql.setDouble(3,
                    Double.parseDouble(Double.toString(salario)
                            .replace(',', '.')));
            querySql.execute();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    public boolean deleteProfessional (int id) {
        String sql = String.format("DELETE FROM %s WHERE id=?;", this.tableName);
        try {
            PreparedStatement querySql = connection.prepareStatement(sql);
            querySql.setInt(1, id);
            querySql.execute();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    public boolean alterProfessional(Profissional prof) {
        String sql = String.format("UPDATE %s SET nome=?, profissao=?, salario=? WHERE id=?;", this.tableName);
        try {
            PreparedStatement querySql = connection.prepareStatement(sql);
            querySql.setString(1, prof.getNome());
            querySql.setString(2, prof.getProfissao());
            querySql.setDouble(3, prof.getSalario());
            querySql.setInt(4, prof.getId());
            querySql.execute();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    static public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    static void profStatment(Profissional prof, ResultSet query) throws SQLException {
        prof.setId(query.getInt("id"));
        prof.setNome(query.getString("nome"));
        prof.setProfissao(query.getString("profissao"));
        prof.setSalario(query.getDouble("salario"));
    }
}
