import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import '../models/usuario.dart';
import '../models/camion.dart';
import '../models/auth_response.dart';

class ApiService {
  static const String baseUrl = 'http://10.0.2.2:8080/api';
  
  String? _token;

  Future<void> _loadToken() async {
    final prefs = await SharedPreferences.getInstance();
    _token = prefs.getString('token');
  }

  Future<void> _saveToken(String token) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString('token', token);
    _token = token;
  }

  Future<void> clearToken() async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.remove('token');
    _token = null;
  }

  Map<String, String> _getHeaders() {
    final headers = {
      'Content-Type': 'application/json',
    };
    if (_token != null) {
      headers['Authorization'] = 'Bearer $_token';
    }
    return headers;
  }

  Future<AuthResponse> login(String username, String password) async {
    
    final response = await http.post(
      Uri.parse('$baseUrl/auth/login'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        'username': username,
        'password': password,
      }),
    );

    if (response.statusCode == 200) {
      final authResponse = AuthResponse.fromJson(jsonDecode(response.body));
      await _saveToken(authResponse.token);
      return authResponse;
    } else {
      throw Exception('Error en login: ${response.body}');
    }
  }

  Future<AuthResponse> register(String username, String email, String password, String empresa) async {
    final response = await http.post(
      Uri.parse('$baseUrl/auth/register'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        'username': username,
        'email': email,
        'password': password,
        'empresa': empresa,
      }),
    );

    if (response.statusCode == 200 || response.statusCode == 201) {
      final authResponse = AuthResponse.fromJson(jsonDecode(response.body));
      await _saveToken(authResponse.token);
      return authResponse;
    } else {
      throw Exception('Error en registro: ${response.body}');
    }
  }

  Future<List<Camion>> getCamiones() async {
    await _loadToken();
    final response = await http.get(
      Uri.parse('$baseUrl/camiones'),
      headers: _getHeaders(),
    );

    if (response.statusCode == 200) {
      final List<dynamic> data = jsonDecode(response.body);
      return data.map((json) => Camion.fromJson(json)).toList();
    } else {
      throw Exception('Error al obtener camiones: ${response.body}');
    }
  }

  Future<Camion> createCamion(String placa, String tipoCamion) async {
    await _loadToken();
    final response = await http.post(
      Uri.parse('$baseUrl/camiones'),
      headers: _getHeaders(),
      body: jsonEncode({
        'placa': placa,
        'tipoCamion': tipoCamion,
      }),
    );

    if (response.statusCode == 200 || response.statusCode == 201) {
      return Camion.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Error al crear camión: ${response.body}');
    }
  }

  Future<void> deleteCamion(int id) async {
    await _loadToken();
    final response = await http.delete(
      Uri.parse('$baseUrl/camiones/$id'),
      headers: _getHeaders(),
    );

    if (response.statusCode != 200 && response.statusCode != 204) {
      throw Exception('Error al eliminar camión: ${response.body}');
    }
  }
}
