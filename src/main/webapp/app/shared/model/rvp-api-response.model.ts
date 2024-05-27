export interface IRvpApiResponse {
  id?: number;
  surveyId?: string | null;
  lodgingId?: number | null;
  date?: Date | null;
  overallsatsifaction?: number | null;
  customScore?: number | null;
  plantorevisit?: boolean | null;
  label?: string | null;
}

export class RvpApiResponse implements IRvpApiResponse {
  constructor(
    public id?: number,
    public surveyId?: string | null,
    public lodgingId?: number | null,
    public date?: Date | null,
    public overallsatsifaction?: number | null,
    public customScore?: number | null,
    public plantorevisit?: boolean | null,
    public label?: string | null,
  ) {
    this.plantorevisit = this.plantorevisit ?? false;
  }
}
