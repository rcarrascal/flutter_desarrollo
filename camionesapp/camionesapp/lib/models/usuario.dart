class Usuario {
  final int? id;
  final String username;
  final String email;
  final String? empresa;

  Usuario({
    this.id,
    required this.username,
    required this.email,
    this.empresa,
  });

  factory Usuario.fromJson(Map<String, dynamic> json) {
    return Usuario(
      id: json['id'],
      username: json['username'],
      email: json['email'],
      empresa: json['empresa'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'username': username,
      'email': email,
      'empresa': empresa,
    };
  }
}
