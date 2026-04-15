class Camion {
  final int? id;
  final String placa;
  final String tipoCamion;
  final int? usuarioId;

  Camion({
    this.id,
    required this.placa,
    required this.tipoCamion,
    this.usuarioId,
  });

  factory Camion.fromJson(Map<String, dynamic> json) {
    return Camion(
      id: json['id'],
      placa: json['placa'],
      tipoCamion: json['tipoCamion'],
      usuarioId: json['usuarioId'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'placa': placa,
      'tipoCamion': tipoCamion,
      'usuarioId': usuarioId,
    };
  }
}
