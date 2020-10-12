import { TestBed, async } from '@angular/core/testing';
import { NgxsModule, Store } from '@ngxs/store';
import { AvailableDateListState } from './available-date-list.state';

describe('AvailableDateList store', () => {
  let store: Store;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [NgxsModule.forRoot([AvailableDateListState])]
    }).compileComponents();
    store = TestBed.get(Store);
  }));



});
