export interface IRvpApilodging {
  id?: string;
  name?: string | null;
}

export class RvpApilodging implements IRvpApilodging {
  constructor(
    public id?: string,
    public name?: string | null,
  ) {}
}
