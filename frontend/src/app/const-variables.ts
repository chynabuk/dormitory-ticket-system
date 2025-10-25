import { Params } from "@angular/router";

export const api = 'http://10.42.70.52:8082/api';
export const user = '/user';
export const issues = '/issues';

export const status: Params = {
    "created": "Erstellt",
    "inProgress": "Im Prozess",
    "resolved": "Abgeschlossen",
    "discarded": "Weggeworfen",
    "CREATED": "Erstellt",
    "IN_PROGRESS": "Im Prozess",
    "RESOLVED": "Abgeschlossen",
    "DISCARDED": "Weggeworfen"
}