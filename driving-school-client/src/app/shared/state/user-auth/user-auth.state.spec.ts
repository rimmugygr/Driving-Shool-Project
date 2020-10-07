import { TestBed, async } from '@angular/core/testing';
import { NgxsModule, Store } from '@ngxs/store';
import { UserAuthState} from './user-auth.state';

describe('UserAuth store', () => {
  let store: Store;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [NgxsModule.forRoot([UserAuthState])]
    }).compileComponents();
    store = TestBed.get(Store);
  }));


});
